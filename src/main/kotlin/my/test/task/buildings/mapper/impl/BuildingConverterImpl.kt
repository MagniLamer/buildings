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

internal const val BUILDING_ID_PARAM = "buildingId"
internal const val BUILDING_NAME_PARAM = "buildingName"
internal const val DESCRIPTION_PARAM = "description"
internal const val CITY_PARAM = "city"
internal const val COUNTRY_PARAM = "country"
internal const val STREET_PARAM = "street"
internal const val NUMBER_PARAM = "number"
internal const val POSTAL_CODE_PARAM = "postalCode"

@Service
class BuildingConverterImpl : BuildingConverter {
    fun convertEntityToBuilding(buildingEntity: BuildingEntity): Building? {
        return null
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

    override fun convertMapToBuilding(request: Map<String, String>): Building {
        return Building.BuildingBuilder.newBuilder()
            .id(request[BUILDING_ID_PARAM]?.let { UUID.fromString(it) })
            .name(request[BUILDING_NAME_PARAM])
            .description(request[DESCRIPTION_PARAM])
            .buildingAddress(
                BuildingAddress.BuildingAddressBuilder.newBuilder()
                    .city(request[CITY_PARAM])
                    .country(request[COUNTRY_PARAM])
                    .street(request[STREET_PARAM])
                    .number(request[NUMBER_PARAM]?.toInt())
                    .postalCode(request[POSTAL_CODE_PARAM])
                    .build()
            )
            .build()
    }
}