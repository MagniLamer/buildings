package my.test.task.buildings.service

import my.test.task.buildings.domain.model.Building

interface CoordinateService {
    fun buildBuildingWithCoordinates(request: Map<String, String>): Building
}