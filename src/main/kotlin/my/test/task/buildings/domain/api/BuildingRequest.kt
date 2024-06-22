package my.test.task.buildings.domain.api

import my.test.task.buildings.domain.entity.BuildingEntity

data class BuildingRequest (
    val buildings: List<BuildingEntity>
    )