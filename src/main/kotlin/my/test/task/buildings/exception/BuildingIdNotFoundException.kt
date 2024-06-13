package my.test.task.buildings.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Building with id didn't find")
class BuildingIdNotFoundException :
    RuntimeException()