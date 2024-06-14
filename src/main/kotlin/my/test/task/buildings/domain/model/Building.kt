package my.test.task.buildings.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class Building internal constructor(
    @JsonProperty("buildingId")
    val id:UUID?=null,
    val name: String? = null,
    val buildingAddress: BuildingAddress? = null,
    val description: String? = null
) {

    class BuildingBuilder internal constructor() {
        private var id:UUID? = null
        private var name: String? = null
        private var buildingAddress: BuildingAddress? = null
        private var description: String? = null

        fun id(id: UUID?): BuildingBuilder {
            this.id = id
            return this
        }

        fun name(name: String?): BuildingBuilder {
            this.name = name
            return this
        }

        fun buildingAddress(buildingAddress: BuildingAddress?): BuildingBuilder {
            this.buildingAddress = buildingAddress
            return this
        }

        fun description(description: String?): BuildingBuilder {
            this.description = description
            return this
        }

        fun build(): Building {
            return Building(
                id=id,
                name = name,
                buildingAddress = buildingAddress,
                description = description
            )
        }

        companion object {
            fun newBuilder(): BuildingBuilder {
                return BuildingBuilder()
            }

            fun newBuilder(src: Building?): BuildingBuilder {
                val builder = BuildingBuilder()
                if (src != null) {
                    builder
                        .id(src.id)
                        .name(src.name)
                        .buildingAddress(src.buildingAddress)
                        .description(src.description)

                }
                return builder
            }
        }
    }
}

data class BuildingAddress internal constructor(
    val street: String? = null,
    val number: Int? = null,
    val postalCode: String? = null,
    val city: String? = null,
    val country: String? = null,
    val coordinates: BuildingCoordinates? = null
){
    class BuildingAddressBuilder internal constructor() {
        private var street: String? = null
        private var number: Int? = null
        private var postalCode: String? = null
        private var city: String? = null
        private var country: String? = null
        private var coordinates: BuildingCoordinates? = null

        fun street(street: String?): BuildingAddressBuilder {
            this.street = street
            return this
        }

        fun number(number: Int?): BuildingAddressBuilder {
            this.number = number
            return this
        }

        fun postalCode(postalCode: String?): BuildingAddressBuilder {
            this.postalCode = postalCode
            return this
        }

        fun city(city: String?): BuildingAddressBuilder {
            this.city = city
            return this
        }

        fun country(country: String?): BuildingAddressBuilder {
            this.country = country
            return this
        }

        fun coordinates(coordinates: BuildingCoordinates?): BuildingAddressBuilder {
            this.coordinates = coordinates
            return this
        }

        fun build(): BuildingAddress {
            return BuildingAddress(
                street = street,
                number = number,
                postalCode = postalCode,
                city = city,
                country = country,
                coordinates = coordinates
            )
        }

        companion object {
            fun newBuilder(): BuildingAddressBuilder {
                return BuildingAddressBuilder()
            }

            fun newBuilder(src: BuildingAddress?, coordinates: BuildingCoordinates?): BuildingAddressBuilder {
                val builder = BuildingAddressBuilder()
                if (src != null) {
                    builder
                        .street(src.street)
                        .number(src.number)
                        .postalCode(src.postalCode)
                        .city(src.city)
                        .country(src.country)
                        .coordinates(coordinates)
                }
                return builder
            }
        }
    }
}

data class BuildingCoordinates internal constructor(
    val coordinateY: String? = null,
    val coordinateX: String? = null
){
    class BuildingCoordinatesBuilder internal constructor() {
        private var coordinateY: String? = null
        private var coordinateX: String? = null

        fun coordinateY(coordinateY: String?): BuildingCoordinatesBuilder {
            this.coordinateY = coordinateY
            return this
        }

        fun coordinateX(coordinateX: String?): BuildingCoordinatesBuilder {
            this.coordinateX = coordinateX
            return this
        }

        fun build(): BuildingCoordinates {
            return BuildingCoordinates(
                coordinateY = coordinateY,
                coordinateX = coordinateX
            )
        }

        companion object {
            fun newBuilder(): BuildingCoordinatesBuilder {
                return BuildingCoordinatesBuilder()
            }

            fun newBuilder(src: BuildingCoordinates?): BuildingCoordinatesBuilder {
                val builder = BuildingCoordinatesBuilder()
                if (src != null) {
                    builder
                        .coordinateY(src.coordinateY)
                        .coordinateX(src.coordinateX)
                }
                return builder
            }
        }
    }
}