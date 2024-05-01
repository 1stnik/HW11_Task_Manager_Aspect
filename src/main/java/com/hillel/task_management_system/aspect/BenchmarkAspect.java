package com.hillel.task_management_system.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BenchmarkAspect {

    @Pointcut("execution(public * (" +
            "com.hillel.task_management_system.service_auth.AuthService || " +
            "com.hillel.task_management_system.service.TaskService || " +
            "com.hillel.task_management_system.service.UserService).*(..))")
    public void benchmarkPointcut() {

    }

    @Around("benchmarkPointcut()")
    public Object benchmarkJoinPointLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(proceedingJoinPoint.getSignature() + " Executed time: " + executionTime + " ms");
        return proceed;
    }

}
