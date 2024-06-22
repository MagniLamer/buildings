package my.test.task.buildings.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.mapper.BuildingConverter
import my.test.task.buildings.service.CoordinateService
import my.test.task.buildings.service.GeoRequestService
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.stereotype.Service


@Slf4j
@Service
class CoordinateServiceImpl(
    private val customObjectMapper: ObjectMapper,
    private val buildingConverter: BuildingConverter,
    private val geoRequestService: GeoRequestService
) : CoordinateService {

    override fun addCoordinatesToBuilding(buildingEntity:  BuildingEntity): BuildingEntity {

        val responseDetails = geoRequestService.performRequest( buildingEntity).get()
        val geoResponse: GeoResponse? = try {
            customObjectMapper.readValue(responseDetails.response.body, GeoResponse::class.java)
        } catch (ex: Exception) {
            log.error(
                """CoordinateServiceImpl - Mapping a geo response error
                |response - ${responseDetails.response.body}""".trimMargin(), ex
            )
            null
        }

        return buildingConverter.addCoordinateToBuilding(buildingEntity, geoResponse)
    }
}