package my.test.task.buildings.service

import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building

interface CoordinateService {
    fun addCoordinatesToBuilding(building: BuildingEntity): BuildingEntity
}