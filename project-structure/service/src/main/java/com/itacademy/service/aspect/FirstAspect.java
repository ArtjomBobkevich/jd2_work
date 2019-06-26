package com.itacademy.service.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import static org.apache.log4j.LogManager.getLogger;

@Aspect
@Component
public class FirstAspect {

    @Pointcut("execution(public * com.itacademy.service.service.*Service.*(..))")
    public void addLogging () {}

    @Before("addLogging()")
    public void before () {
        System.out.println("Begin Logging...");
    }

    @AfterThrowing("addLogging()")
    public void afterLikeFinally () {
        System.out.println("Problem was at Service...");
    }

    @AfterReturning("addLogging()")
    public void after () {
        System.out.println("Logging was completed!");
    }

    @Around("execution(public * com.itacademy.service.service.*Service.*(..))")
    public Object logMethod(final ProceedingJoinPoint joinPoint)
            throws Throwable {
        final Class<?> targetClass = joinPoint.getTarget().getClass();
        final Logger logger = getLogger(targetClass);
        try {
            final String className = targetClass.getSimpleName();
            logger.debug(getPreMessage(joinPoint, className));
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            final Object retVal = joinPoint.proceed();
            stopWatch.stop();
            logger.debug(getPostMessage(joinPoint, className, stopWatch.getTotalTimeMillis()));
            return retVal;
        } catch ( final Throwable ex ) {
            logger.error(getErrorMessage(ex), ex);
            throw ex;
        }
    }

    private static String getPreMessage(final JoinPoint joinPoint, final String className) {
        final StringBuilder builder = new StringBuilder()
                .append("Entered in ").append(className).append(".")
                .append(joinPoint.getSignature().getName())
                .append("(");
        appendTo(builder, joinPoint);
        return builder
                .append(")")
                .toString();
    }

    private static String getPostMessage(final JoinPoint joinPoint, final String className, final long millis) {
        return "Exit from " + className + "." +
                joinPoint.getSignature().getName() +
                "(..); Execution time: " +
                millis +
                " ms;";
    }

    private static String getErrorMessage(final Throwable ex) {
        return ex.getMessage();
    }

    private static void appendTo(final StringBuilder builder, final JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        for ( int i = 0; i < args.length; i++ ) {
            if ( i != 0 ) {
                builder.append(", ");
            }
            builder.append(args[i]);
        }
    }
}