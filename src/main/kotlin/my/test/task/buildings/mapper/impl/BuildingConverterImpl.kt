package my.test.task.buildings.mapper.impl

import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingAddress
import my.test.task.buildings.domain.model.BuildingCoordinates
import my.test.task.buildings.mapper.BuildingConverter
import org.springframework.stereotype.Service

@Service
class BuildingConverterImpl : BuildingConverter {
    override fun convertEntityToBuilding(buildingEntity: BuildingEntity): Building {
        TODO("Not yet implemented")
    }

    override fun convertBuildingToEntity(building: Building): BuildingEntity {
        return with(building.buildingAddress) {
            val address = building.buildingAddress
            BuildingEntity(
                name = building.name,
                street = address?.street,
                number = address?.number,
                postalCode = address?.postalCode,
                city = address?.city,
                country = address?.country,
                coordinate_x = address?.coordinates?.coordinateX,
                coordinate_y = address?.coordinates?.coordinateY,
                description = building.description
            )
        }
    }

    override fun addCoordinateToBuilding(building: Building, geoResponse: GeoResponse?): Building {
        val coordinates = geoResponse?.features?.first()?.geometry?.coordinates
        return Building.BuildingBuilder.newBuilder()
            .name(building.name)
            .description(building.description)
            .buildingAddress(
                BuildingAddress.BuildingAddressBuilder.newBuilder(
                    building.buildingAddress,
                    BuildingCoordinates.BuildingCoordinatesBuilder.newBuilder()
                        .coordinateX(coordinates?.get(0))
                        .coordinateY(coordinates?.get(1))
                        .build()
                )
                    .build()
            )
            .build()
    }

    override fun convertMapToBuilding(request: Map<String, String>): Building =
        Building.BuildingBuilder.newBuilder()
            .name(request["buildingName"])
            .description(request["description"])
            .buildingAddress(
                BuildingAddress.BuildingAddressBuilder.newBuilder()
                    .city(request["city"])
                    .country(request["country"])
                    .street(request["street"])
                    .number(request["number"]?.toInt())
                    .postalCode(request["postalCode"])
                    .build()
            )
            .build()
}