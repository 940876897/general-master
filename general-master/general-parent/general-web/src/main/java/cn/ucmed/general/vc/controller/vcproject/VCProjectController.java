package cn.ucmed.general.vc.controller.vcproject;

import cn.ucmed.general.common.constants.GlobalConstants;
import cn.ucmed.general.common.constants.model.DefaultDBValue;
import cn.ucmed.general.vc.model.VCProjectModel;
import cn.ucmed.general.vc.service.VCProjectService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhengfuqiang
 * @Description 项目
 * @date 2015年7月24日 下午2:07:27
 */
@Controller
@RequestMapping("/vcProject")
public class VCProjectController {

    private static final Logger LOG = Logger
            .getLogger(VCProjectController.class);

    @Autowired
    private VCProjectService vcProjectService;


    /**
     * @param request
     * @throws Exception
     * @Description 项目列表
     */
    @RequiresPermissions(PermissionConstants.PROJECT_VIEW)
    @RequestMapping(method = RequestMethod.GET, value = "list.json")
    @ResponseBody
    public PaginationResult<VCProjectModel> listGet(
            HttpServletRequest request) throws Exception {
        return vcProjectService.getPaginatedList(
                Long.parseLong(request.getParameter("currentPageNo")),
                Long.parseLong(request.getParameter("pageSize")), request.getParameter("searchValue"));
    }

    /**
     * @Description 添加新项目
     */
    @RequiresPermissions(PermissionConstants.PROJECT_CREATE)
    @RequestMapping(method = RequestMethod.POST, value = "add.json")
    @ResponseBody
    public JSONObject addPost(HttpServletRequest request, @RequestBody VCProjectModel model) {

        // 检测新的项目名称是否已经存在
        VCProjectModel vcProjectModel = vcProjectService
                .findByProjectName(model.getProjectName());
        if (null != vcProjectModel) {
            return ResultUtil.getResult(1, "项目（" + model.getProjectName() + "）已经存在");
        }

        model.setVcProjectId(UUID.randomUUID().toString());
        model.setCreatedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        model.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        model.setDeletionState(DefaultDBValue.CREATE_DELETION_STATE);

        model.setCreatedon(new Date());
        model.setModifiedon(new Date());
        vcProjectService.add(model);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * @Description 修改项目
     */
    @RequiresPermissions(PermissionConstants.PROJECT_UPDATE)
    @RequestMapping(method = RequestMethod.POST, value = "update.json")
    @ResponseBody
    public JSONObject updatePut(HttpServletRequest request, @RequestBody VCProjectModel model) {

        // 检测新的项目名称是否已经存在
        VCProjectModel vcProjectModel = vcProjectService
                .findByProjectName(model.getProjectName());
        if (null != vcProjectModel
                && !vcProjectModel.getVcProjectId().equals(
                model.getVcProjectId())) {
            return ResultUtil.getResult(1, "项目（" + model.getProjectName() + "）已经存在");
        }

        model.setModifiedby(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        model.setModifiedon(new Date());
        vcProjectService.update(model);
        return ResultUtil.getResult(GlobalConstants.RETURN_SUCCESS_CODE, GlobalConstants.RETURN_SUCCESS_INFO);
    }

    /**
     * 搜索项目
     *
     * @param request
     * @param keyword
     * @return
     */
    @RequiresPermissions(PermissionConstants.PROJECT_SEARCH)
    @RequestMapping(method = RequestMethod.GET, value = "search")
    @ResponseBody
    public List<VCProjectModel> search(
            HttpServletRequest request, @PathVariable String keyword) {
        Long pageNo = 1L;
        Long pageSize = 200000000L;
        String p = request.getParameter("page");
        if (null != p) {
            try {
                pageNo = Long.parseLong(p);
                pageSize = Long.parseLong(request.getParameter("pageSize"));
            } catch (Exception e) {
                LOG.error(StringUtils.EMPTY, e);
            }
        }
        PaginationResult<VCProjectModel> pr = vcProjectService.search(keyword,
                pageNo, pageSize);
        return pr.getList();
    }


    @RequestMapping(method = RequestMethod.GET, value = "hospitalAndProjectList.json")
    @ResponseBody
    public String hospitalList(HttpServletRequest request) {
        List<VCProjectModel> hlist = vcProjectService.loadHospitalList();
        List<VCProjectModel> plist = vcProjectService.loadProjectList();
        JSONObject list = new JSONObject();
        list.put("hlist", hlist);
        list.put("plist", plist);
        return list.toString();
    }


    @RequestMapping(method = RequestMethod.GET, value = "projectListByHospitalName.json")
    @ResponseBody
    public List<VCProjectModel> projectListById(HttpServletRequest request) throws UnsupportedEncodingException {
        return vcProjectService.loadProjectListByHospitalName(request.getParameter("hospitalName"));
    }


    @RequestMapping(method = RequestMethod.GET, value = "hospitalProjectList.json")
    @ResponseBody
    public String hospitalProjectList(HttpServletRequest request) {
        List<VCProjectModel> hlist = vcProjectService.loadHospitalProjectList();
        JSONObject list = new JSONObject();
        list.put("hlist", hlist);
        return list.toString();
    }


}
