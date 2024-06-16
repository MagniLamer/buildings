package my.test.task.buildings.aspect

import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.annotation.LogRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.stereotype.Component

/**
 * Used for a log adding for all methods
 */
@Aspect
@Slf4j
@Component
class LogRequestAspect {

    @Around("@annotation(logRequest)")
    fun logRequest(proceedingJoinPoint: ProceedingJoinPoint, logRequest: LogRequest): Any? {
        log.info("[BUILDING SERViCE] Accepted request, payload ${proceedingJoinPoint.args.firstOrNull()}")

        val result = proceedingJoinPoint.proceed()

        log.info("[BUILDING SERViCE] Request processed successfully")
        return result
    }
}
