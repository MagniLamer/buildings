package my.test.task.buildings.mapper.impl

import my.test.task.buildings.domain.api.GeoResponse
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.BuildingAddress
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

    override fun updateBuildingEntity(newBuilding: BuildingEntity, oldBuilding: BuildingEntity): BuildingEntity {
        return BuildingEntity(
            id = oldBuilding.id,
            name = newBuilding.name ?: oldBuilding.name,
            street = newBuilding.street ?: oldBuilding.street,
            number = newBuilding.number ?: oldBuilding.number,
            postalCode = newBuilding.postalCode ?: oldBuilding.postalCode,
            city = newBuilding.city ?: oldBuilding.city,
            country = newBuilding.country ?: oldBuilding.country,
            coordinate_x = newBuilding.coordinate_x ?: oldBuilding.coordinate_x,
            coordinate_y = newBuilding.coordinate_y ?: oldBuilding.coordinate_y,
            description = newBuilding.description ?: oldBuilding.description
        )
    }

    override fun addCoordinateToBuilding(buildingEntity: BuildingEntity, geoResponse: GeoResponse?): BuildingEntity =
        with(buildingEntity) {
            val coordinates = geoResponse?.features?.first()?.geometry?.coordinates
            BuildingEntity(
                id = id,
                name = name,
                street = street,
                number = number,
                postalCode = postalCode,
                city = city,
                country = country,
                coordinate_x = coordinates?.get(1),
                coordinate_y = coordinates?.get(0),
                description = description
            )

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