package org.example.project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @AfterReturning(pointcut = "execution(* org.example.project.service..*transfer*(..))", returning = "result")
    public void logSuccessfulTransfer(JoinPoint joinPoint, Object result) {
        logger.info("[AUDIT SUCCESS] {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* org.example.project.service..*transfer*(..))", throwing = "ex")
    public void logFailedTransfer(JoinPoint joinPoint, Exception ex) {
        logger.error("[AUDIT FAILED] {} - Error: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}