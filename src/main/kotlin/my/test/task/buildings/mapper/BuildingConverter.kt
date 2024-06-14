package my.test.task.buildings.mapper

import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingDTO
import org.springframework.stereotype.Service

@Service
interface BuildingConverter {

    fun convertEntityToBuilding(buildingEntities: List<Building>): List<BuildingDTO>

    fun convertBuildingToEntity(building: Building): BuildingEntity

    fun updateBuildingEntity(building: Building, buildingEntity: BuildingEntity): BuildingEntity

    fun addCoordinateToBuilding(building: Building, geoResponse: GeoResponse?): Building
     fun convertMapToBuilding(request: Map<String, String>): Building
}