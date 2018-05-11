package cn.ucmed.general.vc.controller.vcsoftwareversion;

import cn.ucmed.common.util.ResultUtil;
import cn.ucmed.general.common.util.FileUploadConfig;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    private static final Logger LOG = Logger
            .getLogger(FileUploadController.class);

    public enum FileType {
        ZIP("zip"), APP("app");

        private String name;

        FileType(String sName) {
            this.name = sName;
        }
    }

    @RequestMapping(value = "uploadTest.json", method = RequestMethod.POST)
    public void uploadController(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        JSONObject res = new JSONObject();

        try {
            FileType fileType = request.getParameter("fileType").equals(FileType.ZIP.name) ? FileType.ZIP
                    : FileType.APP;
            String fileServerFullPath = upLoadFile(
                    ((MultipartRequest) request).getFile("file"), fileType, request
                            .getParameter("commonName"), request.getParameter("versionNumber"));

            Boolean upLoadSucess = !fileServerFullPath.isEmpty();
            res.put("isSuccess", upLoadSucess ? "Y" : "N");
            res.put("desc", upLoadSucess ? "文件上传成功!" : "文件上传失败，请重试");

            if (upLoadSucess) {
                res.put("url", fileUploadConfig.getHostName()
                        + fileUploadConfig.getFolder() + fileServerFullPath);
            }
            return;
        } catch (Exception e) {
            res = new JSONObject();
            res.put("isSuccess", "N");
            res.put("desc", "文件上传失败，请重试");
            LOG.error("", e);
        } finally {
            ResultUtil.writeResult(response, res.toString());
        }
    }

    /**
     * @param fileName  客户端文件名
     * @param fileType  传入的文件类型，zip或app
     * @return
     * @Description
     */
    private String getFileTargetPath(FileType fileType, String fileName, String commonName,
                                     String versionNumber) {

        String fileSavePathInTomcat = StringUtils.EMPTY;
        // apk or ipa file
        if (fileType.equals(FileType.APP)) {
            Integer indexNum = commonName.lastIndexOf(".");
            String common = commonName.substring(0, indexNum);
            fileSavePathInTomcat = File.separator + common + versionNumber
                    + commonName.substring(indexNum);
        } else {
            // zip:用时间做zip文件名,并且保留zip文件的后缀
            fileSavePathInTomcat = File.separator + System.currentTimeMillis()
                    + fileName.substring(fileName.lastIndexOf("."));
        }
        return fileSavePathInTomcat;
    }

    private String upLoadFile(MultipartFile mpFile, FileType fileType, String commonName,
                              String versionNumber) {
        String fileTargetPath = getFileTargetPath(fileType, mpFile.getOriginalFilename(),
                commonName, versionNumber);
        String common = "resource";
        if (!"无".equals(commonName)) {
            common = commonName.substring(0, commonName.lastIndexOf("."));
        }
        return saveFile(mpFile, common, fileTargetPath) ? File.separator + common + fileTargetPath
                : StringUtils.EMPTY;
    }

    private boolean saveFile(MultipartFile mpFile, String common, String fileTargetPath) {

        String dir = fileUploadConfig.getUploadRoot()
                + fileUploadConfig.getFolder() + File.separator + common;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(dir + fileTargetPath));
            fos.write(mpFile.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            LOG.error("", e);
        } catch (IOException e) {
            LOG.error("", e);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                LOG.error("", e);
            }
        }
        return false;
    }
}
