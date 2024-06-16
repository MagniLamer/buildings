package my.test.task.buildings.webclient

import my.test.task.buildings.domain.model.ResponseDetails
import org.springframework.util.MultiValueMap
import java.util.concurrent.CompletableFuture

interface WebClientService {

    fun performGetRequest(
        apiUrl: String,
        params: MultiValueMap<String, String>,
        headers: MultiValueMap<String, String>?,
        timeoutInMillis: Long?
    ): CompletableFuture<ResponseDetails>

}
