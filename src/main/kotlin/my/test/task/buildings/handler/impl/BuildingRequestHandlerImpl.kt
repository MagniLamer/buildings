package my.test.task.buildings.handler.impl

import jakarta.persistence.criteria.Predicate
import jakarta.transaction.Transactional
import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.domain.api.BuildingFilterRequest
import my.test.task.buildings.domain.api.BuildingRequest
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingDTO
import my.test.task.buildings.exception.BuildingIdNotFoundException
import my.test.task.buildings.exception.IncorrectBuildingIdException
import my.test.task.buildings.handler.BuildingRequestHandler
import my.test.task.buildings.mapper.BuildingConverter
import my.test.task.buildings.repository.BuildingJpaRepository
import my.test.task.buildings.service.CoordinateService
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.util.StringUtils
import java.util.UUID
import java.util.concurrent.CompletableFuture

internal const val BUILDING = "buildings"

@Slf4j
@Service
class BuildingRequestHandlerImpl(
    private val buildingJpaRepository: BuildingJpaRepository,
    private val buildingConverter: BuildingConverter,
    private val coordinateService: CoordinateService
) : BuildingRequestHandler {

    @Transactional
    override fun saveBuilding(request: BuildingRequest, model: Model): String {
        for (buildingEntity in request.buildings) {
            val savedWithoutCoordinates = buildingJpaRepository.save(buildingEntity)
            CompletableFuture.runAsync {
                updateBuildingWithCoordinates(buildingEntity, savedWithoutCoordinates.id)
            }
            log.info("The building with id: ${savedWithoutCoordinates.id} \n was saved to DB")
        }
        return "add"
    }

    @Transactional
    override fun updateBuilding(buildingEntity: BuildingEntity, model: Model): String {
        buildingEntity.id?.let {
            updateBuildingWithCoordinates(buildingEntity, it)
            return "update"
        } ?: throw IncorrectBuildingIdException()
    }

    override fun deleteBuilding(buildingId: String, model: Model): String {
        buildingJpaRepository.findById(UUID.fromString(buildingId)).map {
            buildingJpaRepository.delete(it)
            log.info("The building with id $buildingId was deleted")
        }
        return "delete"
    }

    override fun getAllBuildings(model: Model): String {
        val firstPageWithTwentyRecords = PageRequest.of(0, 20)
        val allBuildings = buildingJpaRepository.findAll(firstPageWithTwentyRecords)
        model.addAttribute(BUILDING, allBuildings)
        return "filter"
    }

    override fun filterBuildings(filterRequest: BuildingFilterRequest): List<BuildingEntity> {
        val spec = Specification<BuildingEntity> { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            if (StringUtils.hasText(filterRequest.buildingName)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%${filterRequest.buildingName}%"))
            }
            if (StringUtils.hasText(filterRequest.street)) {
                predicates.add(criteriaBuilder.like(root.get("street"), "%${filterRequest.street}%"))
            }
            if (StringUtils.hasText(filterRequest.city)) {
                predicates.add(criteriaBuilder.like(root.get("city"), "%${filterRequest.city}%"))
            }
            if (StringUtils.hasText(filterRequest.country)) {
                predicates.add(criteriaBuilder.like(root.get("country"), "%${filterRequest.country}%"))
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
        val buildings = buildingJpaRepository.findAll(spec)

        return buildings
    }


    private fun updateBuildingWithCoordinates(building: BuildingEntity, buildingId: UUID?) {
        val buildingWithCoordinates = coordinateService.addCoordinatesToBuilding(building)
        buildingId?.let {
            buildingJpaRepository.findById(it).map {
                val updateBuildingEntity = buildingConverter.updateBuildingEntity(buildingWithCoordinates, it)
                buildingJpaRepository.save(updateBuildingEntity)
                log.info("The building with id: $it  was updated ")
            }.orElseThrow { BuildingIdNotFoundException() }
        }
    }
}