package my.test.task.buildings.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Incorrect signature.")
class IncorrectSignatureException :
    RuntimeException()