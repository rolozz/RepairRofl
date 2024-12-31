package com.repair.repairservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Before("execution(* com.repair..*(..))")
    public void logMethodStart(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArguments = joinPoint.getArgs();
        log.info("Метод {} вызывается с параметрами: {}", methodName, Arrays.toString(methodArguments));
    }


    @AfterReturning(pointcut = "execution(* com.repair..*(..))", returning = "result")
    public void logMethodEnd(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Метод {} успешно выполнен. Возвращаемое значение: {}", methodName, result);
    }


    @AfterThrowing(pointcut = "execution(* com.repair..*(..))", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        log.error("Ошибка при выполнении метода {}: {}", methodName, ex.getMessage(), ex);
    }

    @After("execution(* com.repair..*(..))")
    public void logMethodAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName(); // Имя метода
        log.info("Метод {} выполнен (независимо от результата)", methodName);
    }
}
