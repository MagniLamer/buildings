package my.test.task.buildings.service.impl

import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingAddress
import my.test.task.buildings.domain.model.ResponseDetails
import my.test.task.buildings.webclient.WebClientService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.concurrent.CompletableFuture

internal class GeoRequestServiceImplTest {
    private lateinit var webClientService: WebClientService
    private lateinit var geoRequestServiceImpl: GeoRequestServiceImpl
    private val apiKey = "testApiKey"
    private val geoApiUrl = "http://test.url"

    @BeforeEach
    fun setUp() {
        webClientService = mock()
        geoRequestServiceImpl = mock()
        geoRequestServiceImpl = GeoRequestServiceImpl(webClientService, apiKey, geoApiUrl)
    }

    @Test
    fun `should perform request`() {
        // given
        val building = Building(
            buildingAddress = BuildingAddress(
                number = 1,
                street = "street",
                city = "city",
                postalCode = "postalCode",
                country = "country"
            )
        )

        val responseDetails: ResponseDetails = mock()
        val responseEntity = ResponseEntity("response", HttpStatus.OK)
        given { responseDetails.response }.willReturn(responseEntity)
        val expectedResponse = CompletableFuture.completedFuture(responseDetails)
        given(webClientService.performGetRequest(anyString(), anyOrNull(), anyOrNull(), anyOrNull()))
            .willReturn(expectedResponse)

        // when
        val response = geoRequestServiceImpl.performRequest(building)

        // then
        verify(webClientService).performGetRequest(
            eq(geoApiUrl),
            anyOrNull(),
            eq(HttpHeaders()),
            eq(TIMEOUT_30_SEC)
        )

        assertEquals(expectedResponse, response)
    }
}