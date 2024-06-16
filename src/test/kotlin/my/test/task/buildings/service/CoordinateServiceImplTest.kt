package my.test.task.buildings.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import my.test.task.buildings.domain.api.FeaturesItem
import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.api.Geometry
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.ResponseDetails
import my.test.task.buildings.mapper.BuildingConverter
import my.test.task.buildings.service.impl.CoordinateServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture

class CoordinateServiceImplTest {

    private lateinit var buildingConverter: BuildingConverter
    private lateinit var geoRequestService: GeoRequestService
    private lateinit var coordinateServiceImpl: CoordinateServiceImpl
    private lateinit var customObjectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        customObjectMapper = ObjectMapper().apply {
            registerModule(JavaTimeModule())
            registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
        }
        buildingConverter = mock()
        geoRequestService = mock()
        coordinateServiceImpl = CoordinateServiceImpl(
            customObjectMapper, buildingConverter, geoRequestService
        )
    }

    @Test
    fun `should call coordinate service`() {
        //given
        val body = readFile("geo_response.json")
        val params = emptyMap<String, String>()
        val mockBuilding: Building = mock()
        val responseDetails = ResponseDetails(ResponseEntity(body, HttpStatus.OK))
                given { buildingConverter.convertMapToBuilding(params) }.willReturn(mockBuilding)
        given { geoRequestService.performRequest(mockBuilding) }
            .willReturn(CompletableFuture.completedFuture(responseDetails))

        //when
        coordinateServiceImpl.buildBuildingWithCoordinates(params)

        //then
        verify(buildingConverter).addCoordinateToBuilding(anyOrNull(), anyOrNull())
    }

    private fun readFile(path: String) =
        String(Files.readAllBytes(Paths.get(this.javaClass.classLoader.getResource(path).toURI())))
}