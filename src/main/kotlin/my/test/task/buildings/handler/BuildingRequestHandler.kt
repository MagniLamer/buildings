package my.test.task.buildings.handler

import my.test.task.buildings.domain.api.BuildingFilterRequest
import my.test.task.buildings.domain.model.Building
import org.springframework.ui.Model

interface BuildingRequestHandler {
    fun saveBuilding(request: Map<String, String>, model: Model): String
    fun updateBuilding(request: Map<String, String>, model: Model): String
    fun deleteBuilding(buildingId: String, model: Model): String
    fun getBuildingById(buildingId: String, model: Model): String
    fun getAllBuildings(model: Model):String
    fun filterBuildings(filterRequest: BuildingFilterRequest): List<Building>
}