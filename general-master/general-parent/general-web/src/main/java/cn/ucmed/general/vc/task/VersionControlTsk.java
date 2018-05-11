package cn.ucmed.general.vc.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.common.util.DateUtil;
import cn.ucmed.general.common.util.FileUploadConfig;
import cn.ucmed.general.common.util.VersionUtil;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import cn.ucmed.general.vc.service.VCSoftwareVersionService;
import cn.ucmed.general.vc.service.VersionInfoService;

@Component
public class VersionControlTsk {

    private static final Logger LOG = Logger.getLogger(VersionControlTsk.class);

    @Autowired
    private VCSoftwareVersionService vcSoftwareVersionService;

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Autowired
    private VersionInfoService versionInfoService;

    @Scheduled(fixedDelay = 1 * 10 * 1000)
    public void go() {
        LOG.info("VersionControlTsk is begin:" + DateUtil.getCurrentDateTime());
        versionInfoService.updateToLatestVersion();
        releaseCommonVersion();
        LOG.info("VersionControlTsk is end:" + DateUtil.getCurrentDateTime());
    }

    /**
     * @Description 每隔1小时，将到达发布时间的版本的拷贝到通用名版本中
     * @author zhengfuqiang
     */
    @Transactional
    public void releaseCommonVersion()
    {
        LOG.info("ReleaseCommonVersionTask is begin:"
                + DateUtil.getCurrentDateTime());
        try {
            // 获取发布时间到了但未上架的版本列表
            List<VCSoftwareVersionModel> list = vcSoftwareVersionService
                    .selectNotShelvesVersionList(DateUtil.getCurrentDateTime());
            if(CollectionUtils.isNotEmpty(list)) {
                Map<String, String> temp = new HashMap<String, String>();
                for(VCSoftwareVersionModel model : list) {
                    String commonName = model.getCommonName();// 软件通用名
                    if(StringUtils.isEmpty(commonName)){
                        continue;
                    }
                    String appVersionNumber = model.getAppVersionNumber();// APP版本号
                    String versionNumber = temp.get(commonName);
                    if(StringUtils.isEmpty(versionNumber)
                            || VersionUtil.hasNewVersion(versionNumber, appVersionNumber)) {
                        // 根据软件通用名，拼凑带版本号的名称
                        int indexNum = commonName.lastIndexOf(".");
                        StringBuffer sb = new StringBuffer();
                        String common = commonName.substring(0, indexNum);
                        sb.append(common).append(appVersionNumber)
                                .append(commonName.substring(indexNum));
                        String savePath = fileUploadConfig.getUploadRoot()
                                + fileUploadConfig.getFolder() + File.separator + common;
                        File versionFile = new File(savePath + File.separator
                                + sb.toString());

                        if(versionFile.exists()) {
                            InputStream input = new FileInputStream(versionFile);
                            OutputStream output = new FileOutputStream(
                                    new File(savePath + File.separator
                                            + commonName));
                            FileCopyUtils.copy(input, output);
                        }
                        temp.put(commonName, appVersionNumber);
                    }
                    // 设置为上架
                    model.setIsOffShelves(DefaultDBValue.ON_SHELVES);
                    model.setModifiedon(new Date());
                    vcSoftwareVersionService.update(model);
                }
                temp.clear();
            }
        } catch(Exception e) {
            LOG.info(StringUtils.EMPTY, e);
        }
        LOG.info("ReleaseCommonVersionTask is end:"
                + DateUtil.getCurrentDateTime());
    }
}
