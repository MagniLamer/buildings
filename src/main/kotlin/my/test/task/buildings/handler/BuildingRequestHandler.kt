package my.test.task.buildings.handler

import my.test.task.buildings.domain.api.BuildingRequest
import my.test.task.buildings.domain.api.BuildingResponse
import org.springframework.ui.Model

interface BuildingRequestHandler {
    fun saveBuilding(request: Map<String, String>, model: Model): String
    fun updateBuilding(request: Map<String, String>, model: Model): String
    fun deleteBuilding(buildingId: String, model: Model): String
    fun getBuildingById(buildingId: String, model: Model): String
    fun getAllBuildings(model: Model):String
}