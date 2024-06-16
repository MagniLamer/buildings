package my.test.task.buildings.webclient.impl

import my.test.task.buildings.domain.model.ResponseDetails
import my.test.task.buildings.webclient.WebClientService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClientRequest
import java.net.URI
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.concurrent.CompletableFuture

private const val DEFAULT_TIMEOUT = 60_000L

@Service
class WebClientServiceImpl(
    private val webClient: WebClient
) : WebClientService {

    override fun performGetRequest(
        apiUrl: String,
        params: MultiValueMap<String, String>,
        headers: MultiValueMap<String, String>?,
        timeoutInMillis: Long?
    ): CompletableFuture<ResponseDetails> {
        return webClient
            .get()
            .uri(buildUriFromParams(apiUrl, params))
            .also { setRequestTimeout(it, timeoutInMillis ?: DEFAULT_TIMEOUT) }
            .headers { requestHeaders ->
                if (headers != null) {
                    requestHeaders.addAll(headers)
                }
            }
            .acceptCharset(StandardCharsets.UTF_8)
            .retrieve()
            .toEntity(String::class.java)
            .flatMap {
                Mono.justOrEmpty(ResponseDetails(it))
            }
            .toFuture()
    }

    private fun <S : WebClient.RequestHeadersSpec<S>> setRequestTimeout(
        requestBodySpec: WebClient.RequestHeadersSpec<S>,
        timeoutInMillis: Long?
    ) {
        if (timeoutInMillis != null && timeoutInMillis > 0) {
            requestBodySpec.httpRequest {
                it.getNativeRequest<HttpClientRequest>()
                    .responseTimeout(Duration.ofMillis(timeoutInMillis))
            }
        }
    }

    internal fun buildUriFromParams(
        apiUrl: String,
        requestParams: MultiValueMap<String, String>
    ): String = UriComponentsBuilder.newInstance()
        .uri(URI(apiUrl))
        .queryParams(requestParams).build().toUriString()

}