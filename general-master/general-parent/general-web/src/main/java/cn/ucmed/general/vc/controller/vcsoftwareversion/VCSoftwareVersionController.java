package cn.ucmed.general.vc.controller.vcsoftwareversion;

import cn.ucmed.common.rubikexception.BusinessException;
import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.vc.model.VCProjectModel;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import cn.ucmed.general.vc.service.VCProjectService;
import cn.ucmed.general.vc.service.VCProjectSoftwareService;
import cn.ucmed.general.vc.service.VCSoftwareVersionService;
import cn.ucmed.common.constants.PermissionConstants;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.common.util.ResultUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/vcSoftwareVersion")
public class VCSoftwareVersionController {

    private static final Logger LOG = Logger.getLogger(VCSoftwareVersionController.class);

    @Autowired
    private VCProjectSoftwareService vcProjectSoftwareService;

    @Autowired
    private VCSoftwareVersionService vcSoftwareVersionService;

    @Autowired
    private VCProjectService vcProjectService;

    /**
     * @param request
     * @Description 软件下的版本列表
     */
    @RequiresPermissions(PermissionConstants.SOFTWARE_VERSION)
    @RequestMapping(method = RequestMethod.GET, value = "list.json")
    public
    @ResponseBody
    JSONObject listGet(HttpServletRequest request) {

        String softwareId = request.getParameter("softwareId");
        PaginationResult<VCSoftwareVersionModel> pr = vcSoftwareVersionService
                .getPaginatedList(
                        Long.parseLong(request.getParameter("currentPageNo")),
                        Long.parseLong(request.getParameter("pageSize")),
                        softwareId);

        VCProjectSoftwareModel projectSoftwareModel = vcProjectSoftwareService
                .findBySoftwareId(softwareId);

        VCProjectModel projectModel = vcProjectService
                .findByProjectId(projectSoftwareModel.getVcProjectId());
        JSONObject ret = new JSONObject();
        ret.put("hospital_name", projectModel.getHospitalName());
        ret.put("project_name", projectModel.getProjectName());
        ret.put("software_name", projectSoftwareModel.getSoftwareName());
        ret.put("software_type", projectSoftwareModel.getSoftwareType());
        ret.put("common_name", StringUtils.isNotEmpty(projectSoftwareModel.getCommonName()) ? projectSoftwareModel.getCommonName() : "无");

        // 最大应用版本号、资源版本号
        JSONObject json = vcSoftwareVersionService.getMaxVersionBySoftWareId(softwareId);
        ret.put("maxAppVersion", json.optString("maxAppVersion"));
        ret.put("maxZipVersion", json.optString("maxZipVersion"));
        ret.put("softWareVersionList", pr);

        return ret;
    }

    /**
     * @throws Exception
     * @Description 添加软件版本
     */
    @RequiresPermissions(PermissionConstants.VERSION_CREATE)
    @RequestMapping(method = RequestMethod.POST, value = "add.json")
    public
    @ResponseBody
    JSONObject addPost(HttpServletRequest request, @RequestBody VCSoftwareVersionModel model) throws Exception {
        try {
            vcSoftwareVersionService.add(model);
            return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
        } catch (BusinessException be) {
            LOG.error("", be);
            return ResultUtil.getResult(be.getCode(), be.getMessage());
        }
    }

    /**
     * @Description 修改软件版本
     */
    @RequiresPermissions(PermissionConstants.VERSION_UPDATE)
    @RequestMapping(method = RequestMethod.POST, value = "update.json")
    public
    @ResponseBody
    JSONObject updatePut(HttpServletRequest request, @RequestBody VCSoftwareVersionModel model) {
        try {
            model.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
            model.setModifiedon(new Date());
            vcSoftwareVersionService.update(model);
            return ResultUtil.getResult(0, "success");
        } catch (BusinessException be) {
            LOG.error("", be);
            return ResultUtil.getResult(be.getCode(), be.getMessage());
        } catch (Exception ex) {
            LOG.error("", ex);
            return ResultUtil.getResult(500, ex.getMessage());
        }
    }

    /**
     * @return
     * @throws Exception
     * @Description下架软件版本
     */
    @RequiresPermissions(PermissionConstants.VERSION_UNSHELVE)
    @RequestMapping(method = RequestMethod.POST, value = "offShelves.json")
    public
    @ResponseBody
    JSONObject offShelves(HttpServletRequest request, @RequestBody VCSoftwareVersionModel model) throws Exception {
        try {
            vcSoftwareVersionService.offShelves(model);
            return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
        } catch (BusinessException be) {
            LOG.error("", be);
            return ResultUtil.getResult(be.getCode(), be.getMessage());
        }
    }
}
