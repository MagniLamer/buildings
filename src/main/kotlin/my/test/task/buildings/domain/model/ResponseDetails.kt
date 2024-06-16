package my.test.task.buildings.domain.model

import org.springframework.http.ResponseEntity
    data class ResponseDetails(
        val response: ResponseEntity<String>
    )