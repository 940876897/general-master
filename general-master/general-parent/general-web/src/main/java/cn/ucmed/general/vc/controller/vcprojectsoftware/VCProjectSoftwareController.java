package cn.ucmed.general.vc.controller.vcprojectsoftware;

import cn.ucmed.common.constants.PermissionConstants;
import cn.ucmed.common.util.PaginationResult;
import cn.ucmed.common.util.ResultUtil;
import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.vc.model.VCProjectModel;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.general.vc.service.VCProjectService;
import cn.ucmed.general.vc.service.VCProjectSoftwareService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 软件crud Controller
 */
@Controller
@RequestMapping("/vcProjectSoftware")
public class VCProjectSoftwareController {

    @Autowired
    private VCProjectSoftwareService vcProjectSoftwareService;

    @Autowired
    private VCProjectService vcProjectService;

    /**
     * @throws UnsupportedEncodingException
     * @Description 获取项目软件列表
     */
    @RequiresPermissions(PermissionConstants.PROJECT_SOFTWARE)
    @RequestMapping(method = RequestMethod.GET, value = "projectSoftwareList.json")
    @ResponseBody
    public String softwareList(
            HttpServletRequest request) throws UnsupportedEncodingException {
        String projectId = request.getParameter("vcProjectId");

        PaginationResult<VCProjectSoftwareModel> ptr = vcProjectSoftwareService
                .getProjectSoftwareList(projectId,
                        Long.parseLong(request.getParameter("currentPageNo")),
                        Long.parseLong(request.getParameter("pageSize")));
        VCProjectModel projectModel = vcProjectService.findByProjectId(projectId);

        JSONObject ret = new JSONObject();
        ret.put("hospital_name", projectModel.getHospitalName());
        ret.put("project_name", projectModel.getProjectName());
        ret.put("softWareList", ptr);

        /**
         * Christian: 由于软件model中的最新版本model（latestVersion）为空（即软件中没有任何版本的情况）时，
         * SpringMVC在传递JSOn对象时会进行转换并报错：JsonMappingException: Object is null
         * 所有这里用将JSONObject toString后转码后传给客户端，如果发现更好的办法可以替代此处的做法；
         */
        return ret.toString();
    }


    /**
     * 获取软件列表
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */

    @RequiresPermissions(PermissionConstants.PROJECT_SOFTWARE)
    @RequestMapping(method = RequestMethod.GET, value = "list.json")
    @ResponseBody
    public String list(
            HttpServletRequest request) throws UnsupportedEncodingException {

        PaginationResult<VCProjectSoftwareModel> ptr = vcProjectSoftwareService
                .loadSoftwareList(Long.parseLong(request.getParameter("currentPageNo")),
                        Long.parseLong(request.getParameter("pageSize")));

        JSONObject ret = new JSONObject();
        ret.put("softWareList", ptr);
        return ret.toString();
    }


    /**
     * 左边菜单栏的软件列表
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresPermissions(PermissionConstants.SOFTWARE_VIEW)
    @RequestMapping(method = RequestMethod.GET, value = "hospitalSoftwareList.json")
    @ResponseBody
    public String hospitalSoftwareList(
            HttpServletRequest request) throws UnsupportedEncodingException {
        PaginationResult<VCProjectSoftwareModel> ptr = vcProjectSoftwareService
                .seachSoftwareList(request.getParameter("searchValue"), Long.parseLong(request.getParameter("currentPageNo")),
                        Long.parseLong(request.getParameter("pageSize")));

        JSONObject ret = new JSONObject();
        ret.put("softWareList", ptr);
        return ret.toString();
    }


    /**
     * 搜索软件
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresPermissions(PermissionConstants.SOFTWARE_SEARCH)
    @RequestMapping(method = RequestMethod.GET, value = "seachSoftware.json")
    @ResponseBody
    public String seachSoftware(
            HttpServletRequest request) throws UnsupportedEncodingException {
        PaginationResult<VCProjectSoftwareModel> ptr = vcProjectSoftwareService
                .seachSoftwareList(request.getParameter("searchValue"), Long.parseLong(request.getParameter("currentPageNo")),
                        Long.parseLong(request.getParameter("pageSize")));

        JSONObject ret = new JSONObject();
        ret.put("softWareList", ptr);
        return ret.toString();
    }


    /**
     * @Description 添加项目软件
     */
    @RequiresPermissions(PermissionConstants.SOFTWARE_CREATE)
    @RequestMapping(method = RequestMethod.POST, value = "add.json")
    @ResponseBody
    public JSONObject addProjectSoftware(HttpServletRequest request, @RequestBody VCProjectSoftwareModel vcProjectSoftware) {
        vcProjectSoftwareService.addProjectSoftware(vcProjectSoftware);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * @Description 修改项目软件
     */
    @RequiresPermissions(PermissionConstants.SOFTWARE_UPDATE)
    @RequestMapping(method = RequestMethod.POST, value = "update.json")
    @ResponseBody
    public JSONObject updateProjectSoftware(HttpServletRequest request, @RequestBody VCProjectSoftwareModel vcProjectSoftware) {
        vcProjectSoftwareService.updateProjectSoftware(vcProjectSoftware);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }


    /**
     * 校验通用名是否已经存在
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(method = RequestMethod.GET, value = "commonNameIfExist.json")
    @ResponseBody
    public JSONObject findCommonNameIfExist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        VCProjectSoftwareModel vsm = vcProjectSoftwareService.loadByCommonName(request.getParameter("commonName"));

        JSONObject res = new JSONObject();
        res.put("exists", vsm != null);
        return res;
    }

}
