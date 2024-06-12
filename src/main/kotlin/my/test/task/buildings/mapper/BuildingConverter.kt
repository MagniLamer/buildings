package my.test.task.buildings.mapper

import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import org.springframework.stereotype.Service

@Service
interface BuildingConverter {

    fun convertEntityToBuilding(buildingEntity: BuildingEntity): Building

    fun convertBuildingToEntity(building: Building): BuildingEntity

    fun addCoordinateToBuilding(building: Building, geoResponse: GeoResponse?): Building
     fun convertMapToBuilding(request: Map<String, String>): Building
}