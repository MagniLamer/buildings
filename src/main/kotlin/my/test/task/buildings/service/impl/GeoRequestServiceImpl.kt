package my.test.task.buildings.service.impl

import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.service.GeoRequestService
import my.test.task.buildings.webclient.ResponseDetails
import my.test.task.buildings.webclient.WebClientService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.util.concurrent.CompletableFuture

private const val TEXT_PARAM = "text"
private const val API_KEY_PARAM = "apiKey"
private const val TIMEOUT_30_SEC = 30_000L

@Service
class GeoRequestServiceImpl(
    private val webClient: WebClientService,
    @Value("\${emulator.api_key}")
    private val apiKey: String,
    @Value("\${emulator.geo_api_url}")
    private val geoApiUrl: String,
) : GeoRequestService {

    override fun performRequest(building: Building): CompletableFuture<ResponseDetails> {
        return webClient.performGetRequest(
            apiUrl = geoApiUrl,
            params = buildParams(building),
            headers = HttpHeaders(),
            timeoutInMillis = TIMEOUT_30_SEC
        )
    }

   private fun buildParams(building: Building): MultiValueMap<String, String> =
        LinkedMultiValueMap<String, String>().apply {
            add(TEXT_PARAM, buildAddress(building))
            add(API_KEY_PARAM, apiKey)
        }

    private  fun buildAddress(building: Building): String =
        with(building.buildingAddress) {
            "${this?.number} ${this?.street}, ${this?.city} ${this?.postalCode}, ${this?.country}"
        }
}