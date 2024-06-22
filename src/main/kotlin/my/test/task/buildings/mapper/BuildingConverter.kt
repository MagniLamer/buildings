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

    fun updateBuildingEntity(newBuildingEntity: BuildingEntity, oldBuildingEntity: BuildingEntity): BuildingEntity

    fun addCoordinateToBuilding(building: BuildingEntity, geoResponse: GeoResponse?): BuildingEntity
     fun convertMapToBuilding(request: Map<String, String>): Building
}