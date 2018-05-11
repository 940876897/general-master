package cn.ucmed.rubik.sessionauth;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author 滕立明
 * @ClassName ServiceRequestInterceptor
 * @Description 拦截调用cn.ucmed.general.vc.controller.vcproject.VCProjectController.
 * listGet的所有方法
 * @date 2015年8月17日 下午2:05:28
 */
// @Aspect
// @Component
public class ServiceRequestInterceptor {

    private static final Logger LOGGER = Logger.getLogger("aspect");

    /**
     * @Description
     *              拦截cn.ucmed.general.vc.controller.vcproject.VCProjectController
     *              .listGet方法，验证权限
     */
    // @Pointcut("within(cn.ucmed.general.vc.controller..*)")
    // private void pointCut() {
    // }

    /**
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable Throwable
     * @Description 进入Api方法调用
     */
    // @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.info("general aop invoker<<<<<<<<<<");
        LOGGER.info("Class:" + pjp.getClass());
        LOGGER.info("Target:" + pjp.getTarget());
        LOGGER.info("This:" + pjp.getThis());

        LOGGER.info("Args:");
        for (Object arg : pjp.getArgs()) {
            LOGGER.info(arg.getClass());

            if (arg instanceof HttpServletResponse) {
                // ((HttpServletResponse)
                // arg).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // return null;
            }
        }
        return pjp.proceed();
    }

    // @Before("pointCut()")
    public void doBefore(JoinPoint point) {
        LOGGER.info("<<<<<<<<<<<AOP BEFORE");
    }

    // @After("pointCut()")
    public void doAfter(JoinPoint point) {
    }

}
