package my.test.task.buildings.aspect

import lombok.extern.slf4j.Slf4j
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/*
Catches all exception and return an error page
 */
@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleGlobalException(exception: RuntimeException?): String {
        log.error("Runtime exception happened", exception)
        return "error"
    }
}