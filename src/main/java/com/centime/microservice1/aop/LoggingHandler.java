package com.centime.microservice1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingHandler.class);

    @Before("execution(* com.centime.microservice1..*(..)))")
    public void logBeforeEachMethod(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        LOG.info("Executing: class: " + className + " - method: " + methodName);
    }
}
