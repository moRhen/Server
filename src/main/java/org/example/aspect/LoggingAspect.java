package org.example.aspect;

import static org.example.service.RequestCounter.getRequestCounter;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Around("execution(* org.example.controller..*(..))")
  public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("Starting method {}", joinPoint.getSignature().getName());
    getRequestCounter().addRequestCount();
    Object proceed = joinPoint.proceed();
    log.info("Method {} finished", joinPoint.getSignature().getName());
    return proceed;
  }
}
