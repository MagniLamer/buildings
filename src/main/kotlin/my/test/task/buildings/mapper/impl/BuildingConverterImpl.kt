package my.test.task.buildings.mapper.impl

import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingAddress
import my.test.task.buildings.domain.model.BuildingCoordinates
import my.test.task.buildings.domain.model.BuildingDTO
import my.test.task.buildings.mapper.BuildingConverter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BuildingConverterImpl : BuildingConverter {
    fun convertEntityToBuilding(buildingEntity: BuildingEntity): Building {
        TODO("Not yet implemented")
    }

    override fun convertEntityToBuilding(buildingEntities: List<Building>): List<BuildingDTO> {
        val buildings = mutableListOf<BuildingDTO>()
        for (building in buildingEntities) {
            buildings.add(
                BuildingDTO.BuildingDTOBuilder.newBuilder()
                    .id(building.id.toString())
                    .buildingAddress(building.buildingAddress)
                    .name(building.name)
                    .description(building.description)
                    .build()
            )
        }
        return buildings
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

    override fun updateBuildingEntity(building: Building, buildingEntity: BuildingEntity): BuildingEntity {
        val address = building.buildingAddress
        return BuildingEntity(
            id = building.id,
            name = building.name ?: buildingEntity.name,
            street = address?.street ?: buildingEntity.street,
            number = address?.number ?: buildingEntity.number,
            postalCode = address?.postalCode ?: buildingEntity.postalCode,
            city = address?.city ?: buildingEntity.city,
            country = address?.country ?: buildingEntity.country,
            coordinate_x = address?.coordinates?.coordinateX ?: buildingEntity.coordinate_x,
            coordinate_y = address?.coordinates?.coordinateY ?: buildingEntity.coordinate_y,
            description = building.description ?: buildingEntity.description
        )
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
            .id(request["buildingId"]?.let { UUID.fromString(it) })
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