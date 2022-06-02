package com.bnyte.azir.common.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author bnyte
 * @since 2022/6/2 14:05
 */
@Aspect
public class AssertMenuHandler {

    @Pointcut("@annotation(com.bnyte.azir.common.lang.annotation.aspectj.AssertMenu)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        return point.proceed();
    }

}
