package my.test.task.buildings.aspect

import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.exception.BuildingIdNotFoundException
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.authorization.AuthorizationDeniedException
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

    @ExceptionHandler(AuthorizationDeniedException::class)
    fun handleAuthException(exception: RuntimeException?): ResponseEntity <String>{
        log.error("AuthorizationDeniedException happened", exception)
        return ResponseEntity("Unauthorized.",HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(BuildingIdNotFoundException::class)
    fun handleBuildingNotFoundException(exception: RuntimeException?): ResponseEntity <String>{
        log.error("BuildingIdNotFoundException happened", exception)
        return ResponseEntity("The building is not found.",HttpStatus.BAD_REQUEST)
    }
}