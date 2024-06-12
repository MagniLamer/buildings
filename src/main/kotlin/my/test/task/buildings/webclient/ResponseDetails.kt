package my.test.task.buildings.webclient

import org.springframework.http.ResponseEntity


    data class ResponseDetails(
        val response: ResponseEntity<String>
    )