package cn.ucmed.general.vc.logger;

import cn.ucmed.general.common.util.DateUtil;
import cn.ucmed.general.vc.model.VCProjectModel;
import cn.ucmed.general.vc.model.VCProjectSoftwareModel;
import cn.ucmed.general.vc.model.VCSoftwareVersionModel;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author sbwkl
 * @ClassName VCLogger
 * @Description 用于记录项目、软件、版本的操作记录
 * @date 2015年8月18日 下午4:07:50
 */
@Aspect
@Component
public class VCLogger {

    private static final Logger LOG = Logger.getLogger("operation");

    private static final String TYPE_ADD = "添加";
    private static final String TYPE_EDIT = "修改";
    private static final String TYPE_DELETE = "删除";

    private static final String OBJECT_PROJECT = "项目";
    private static final String OBJECT_SOFTWARE = "项目软件";
    private static final String OBJECT_VERSION = "版本";

    private static final String COMMA = ",";

    private static final String EN_COMMA = "&#44;";

    /**
     * @param point
     * @param res
     * @Description 添加项目时记录用户记录
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcproject.VCProjectController.addPost(..))", returning = "res")
    public void doAfterReturningForAddPost(JoinPoint point, Object res) {
        loginfo(buildProject(point.getArgs(), TYPE_ADD));
    }

    /**
     * @param point
     * @param res
     * @Description 修改项目时记录用户记录
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcproject.VCProjectController.updatePut(..))", returning = "res")
    public void doAfterReturningForUpdatePut(JoinPoint point, Object res) {
        loginfo(buildProject(point.getArgs(), TYPE_EDIT));
    }

    private VCLogModel buildProject(Object[] args, String type) {
        HttpServletRequest request = (HttpServletRequest) args[0];
        VCProjectModel project = (VCProjectModel) args[1];
        VCLogModel vclog = new VCLogModel();
        buildCommon(request, vclog);
        vclog.setOperateType(type);
        vclog.setOperateObject(OBJECT_PROJECT);
        vclog.setObjectId(project.getVcProjectId());
        vclog.setObjectDetail(JSONObject.fromObject(project).toString()
                .replace(COMMA, EN_COMMA));
        return vclog;
    }

    /**
     * @param point
     * @param res
     * @Description 添加软件时记录用户信息
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcprojectsoftware.VCProjectSoftwareController.addProjectSoftware(..))", returning = "res")
    public void doAfterReturningForAddProjectSoftware(JoinPoint point,
                                                      Object res) {
        loginfo(buildSoftware(point.getArgs(), TYPE_ADD));
    }

    /**
     * @param point
     * @param res
     * @Description 修改软件时记录用户信息
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcprojectsoftware.VCProjectSoftwareController.updateProjectSoftware(..))", returning = "res")
    public void doAfterReturningForUpdateProjectSoftware(JoinPoint point,
                                                         Object res) {
        loginfo(buildSoftware(point.getArgs(), TYPE_EDIT));
    }

    private VCLogModel buildSoftware(Object[] args, String type) {
        HttpServletRequest request = (HttpServletRequest) args[0];
        VCProjectSoftwareModel software = (VCProjectSoftwareModel) args[1];
        VCLogModel vclog = new VCLogModel();
        buildCommon(request, vclog);
        vclog.setOperateType(type);
        vclog.setOperateObject(OBJECT_SOFTWARE);
        vclog.setObjectId(software.getVcProjectSoftwareId());
        vclog.setObjectDetail(JSONObject.fromObject(software).toString()
                .replace(COMMA, EN_COMMA));
        return vclog;
    }

    /**
     * @param point
     * @param res
     * @Description 添加版本时记录用户信息
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcsoftwareversion.VCSoftwareVersionController.addPost(..))", returning = "res")
    public void doAfterReturningForVersionAddPost(JoinPoint point,
                                                  Object res) {
        loginfo(buildVersion(point.getArgs(), TYPE_ADD));
    }

    /**
     * @param point
     * @param res
     * @Description 修改版本时记录用户信息
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcsoftwareversion.VCSoftwareVersionController.updatePut(..))", returning = "res")
    public void doAfterReturningForVersionUpdatePut(JoinPoint point,
                                                    Object res) {
        loginfo(buildVersion(point.getArgs(), TYPE_EDIT));
    }

    /**
     * @param point
     * @param res
     * @Description 下架版本时记录用户信息
     */
    @AfterReturning(pointcut = "execution(* cn.ucmed.general.vc.controller.vcsoftwareversion.VCSoftwareVersionController.offShelves(..))", returning = "res")
    public void doAfterReturningForVersionOffShelves(JoinPoint point,
                                                     Object res) {
        loginfo(buildVersion(point.getArgs(), TYPE_DELETE));
    }

    private VCLogModel buildVersion(Object[] args, String type) {
        HttpServletRequest request = (HttpServletRequest) args[0];
        VCSoftwareVersionModel version = (VCSoftwareVersionModel) args[1];
        VCLogModel vclog = new VCLogModel();
        buildCommon(request, vclog);
        vclog.setOperateType(type);
        vclog.setOperateObject(OBJECT_VERSION);
        vclog.setObjectId(version.getVcProjectSoftwareId());
        vclog.setObjectDetail(JSONObject.fromObject(version).toString()
                .replace(COMMA, EN_COMMA));
        return vclog;
    }

    private VCLogModel buildCommon(HttpServletRequest request,
                                   VCLogModel vclog) {
        vclog.setOperationUserIp(request.getRemoteAddr());
        vclog.setDate(DateUtil.simpleDate2(new Date()));

        vclog.setOperationUserId(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        vclog.setOperationUserName(String.valueOf(SecurityUtils.getSubject().getPrincipal()));

        return vclog;
    }

    private void loginfo(VCLogModel vclog) {
        Field[] fields = vclog.getClass().getDeclaredFields();
        String info = StringUtils.EMPTY;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                info += field.get(vclog) + COMMA;
            }
            info = info.substring(0, info.length() - 1);
        } catch (IllegalArgumentException e) {
            LOG.error(StringUtils.EMPTY, e);
        } catch (IllegalAccessException e) {
            LOG.error(StringUtils.EMPTY, e);
        } catch (Exception e) {
            LOG.error(StringUtils.EMPTY, e);
        }
        LOG.info(info);
    }
}
