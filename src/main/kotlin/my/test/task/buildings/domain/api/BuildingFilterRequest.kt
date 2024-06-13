package my.test.task.buildings.domain.api

data class BuildingFilterRequest(
    val buildingName: String,
    val street: String,
    val city: String,
    val country: String
)