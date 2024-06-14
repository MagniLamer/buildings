package my.test.task.buildings.service

import com.fasterxml.jackson.databind.ObjectMapper
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.mapper.BuildingConverter
import my.test.task.buildings.service.impl.CoordinateServiceImpl
import my.test.task.buildings.webclient.ResponseDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture

class CoordinateServiceImplTest {
    private lateinit var customObjectMapper: ObjectMapper
    private lateinit var buildingConverter: BuildingConverter
    private lateinit var geoRequestService: GeoRequestService
    private lateinit var coordinateServiceImpl: CoordinateServiceImpl

    @BeforeEach
    fun setUp() {
        customObjectMapper = Configuration.createObjectMapper()
        buildingConverter = mock()
        geoRequestService = mock()
        coordinateServiceImpl = CoordinateServiceImpl(
            customObjectMapper, buildingConverter, geoRequestService
        )
    }

    @Test
    fun `should validate signature`() {
        //given
        val body = readFile("geo_response.json")
        val params = emptyMap<String, String>()
        val mockBuilding: Building = mock()
        val responseDetails = ResponseDetails(ResponseEntity(body, HttpStatus.OK))
        val expectedResult = Building()
        given { buildingConverter.convertMapToBuilding(params) }.willReturn(mockBuilding)
        given { geoRequestService.performRequest(mockBuilding) }
            .willReturn(CompletableFuture.completedFuture(responseDetails))

        given { buildingConverter.addCoordinateToBuilding(eq( mockBuilding), anyOrNull()) }
            .willReturn(mockBuilding)
        //when
        val actualResult = coordinateServiceImpl.buildBuildingWithCoordinates(params)

        //then
        assertEquals(expectedResult, actualResult)
    }

    private fun readFile(path: String) =
        String(Files.readAllBytes(Paths.get(this.javaClass.classLoader.getResource(path).toURI())))
}