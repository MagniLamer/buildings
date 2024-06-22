package my.test.task.buildings.handler

import my.test.task.buildings.domain.api.BuildingFilterRequest
import my.test.task.buildings.domain.api.BuildingRequest
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingDTO
import org.springframework.ui.Model

interface BuildingRequestHandler {
    fun saveBuilding(buildings: BuildingRequest, model: Model): String
    fun updateBuilding(buildingEntity: BuildingEntity, model: Model): String
    fun deleteBuilding(buildingId: String, model: Model): String
    fun getAllBuildings(model: Model):String
    fun filterBuildings(filterRequest: BuildingFilterRequest): List<BuildingEntity>

}