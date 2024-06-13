package my.test.task.buildings.handler.impl

import jakarta.transaction.Transactional
import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.exception.BuildingIdNotFoundException
import my.test.task.buildings.exception.IncorrectBuildingIdException
import my.test.task.buildings.handler.BuildingRequestHandler
import my.test.task.buildings.mapper.BuildingConverter
import my.test.task.buildings.repository.BuildingJpaRepository
import my.test.task.buildings.service.CoordinateService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.UUID
import kotlin.math.log
import org.hibernate.query.sqm.tree.SqmNode.log

internal const val BUILDING = "buildings"

@Slf4j
@Service
class BuildingRequestHandlerImpl(
    private val buildingJpaRepository: BuildingJpaRepository,
    private val buildingConverter: BuildingConverter,
    private val coordinateService: CoordinateService
) : BuildingRequestHandler {

    @Transactional
    override fun saveBuilding(request: Map<String, String>, model: Model): String {
        val buildingWithCoordinates = coordinateService.buildBuildingWithCoordinates(request)
        val buildingEntity = buildingConverter.convertBuildingToEntity(buildingWithCoordinates)
        buildingJpaRepository.save(buildingEntity)
        log.info("The building $buildingWithCoordinates \n was added to DB")
        return "error"
    }

    @Transactional
    override fun updateBuilding(request: Map<String, String>, model: Model): String {
        val building = buildingConverter.convertMapToBuilding(request)
        building.id?.let {
            buildingJpaRepository.findById(it).map {
                val updateBuildingEntity = buildingConverter.updateBuildingEntity(building, it)
                buildingJpaRepository.save(updateBuildingEntity)
                log.info("The building with id $it updated")
            }.orElseThrow { BuildingIdNotFoundException() }
            return "success"
        } ?: throw IncorrectBuildingIdException()
    }

    override fun deleteBuilding(buildingId: String, model: Model): String {
        buildingJpaRepository.findById(UUID.fromString(buildingId)).map {
            buildingJpaRepository.delete(it)
            log.info("The building with id $buildingId was deleted")
        }
        return "success"
    }

    override fun getBuildingById(buildingId: String, model: Model): String {
        TODO("Not yet implemented")
    }

    override fun getAllBuildings(model: Model): String {
        val firstPageWithTwentyRecords = PageRequest.of(0, 20)
        val allBuildings = buildingJpaRepository.findAll(firstPageWithTwentyRecords)
        model.addAttribute(BUILDING, allBuildings)
        return "main_page"
    }

}