package my.test.task.buildings.domain.api

data class GeoResponse(
	val features: List<FeaturesItem?>? = null,
)

data class FeaturesItem(
	val geometry: Geometry? = null,
)

data class Geometry(
	val coordinates: List<String>,
	val type: String? = null
)