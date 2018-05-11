package cn.ucmed.rubik.monitor;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class BaseMonitor {

    private static final Logger LOG = Logger.getLogger("monitor");

    /**
     * @Description 打印当前切入方法的执行时间，调用栈
     * @param pjp
     *            ProceedingJoinPoint
     * @return Object
     * @throws Throwable
     *             Throwable
     */
    public Object around(ProceedingJoinPoint pjp) throws Throwable
    {
        String path = pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName();

        LOG.info("Method in:" + path);
        long begin = System.nanoTime();
        long end = System.nanoTime();
        String log = " Time cost for method: " + pjp.getSignature().getName() + ":" + (end - begin)
                / 1000000;
        LOG.info(log);
        LOG.info("******************Current stack trace start:******************");

        for(StackTraceElement stElement : Thread.currentThread().getStackTrace()) {
            LOG.info(stElement);
        }

        LOG.info("******************Current stack trace end:******************");
        Object o = pjp.proceed();
        LOG.info("Method out:" + path);
        return o;
    }
}
