package my.test.task.buildings.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class BuildingDTO internal constructor(
    @JsonProperty("buildingId")
    val id:String?=null,
    val name: String? = null,
    val buildingAddress: BuildingAddress? = null,
    val description: String? = null
) {

    class BuildingDTOBuilder internal constructor() {
        private var id:String? = null
        private var name: String? = null
        private var buildingAddress: BuildingAddress? = null
        private var description: String? = null

        fun id(id: String?): BuildingDTO.BuildingDTOBuilder {
            this.id = id
            return this
        }

        fun name(name: String?): BuildingDTO.BuildingDTOBuilder {
            this.name = name
            return this
        }

        fun buildingAddress(buildingAddress: BuildingAddress?): BuildingDTO.BuildingDTOBuilder {
            this.buildingAddress = buildingAddress
            return this
        }

        fun description(description: String?): BuildingDTO.BuildingDTOBuilder {
            this.description = description
            return this
        }

        fun build(): BuildingDTO {
            return BuildingDTO(
                id=id,
                name = name,
                buildingAddress = buildingAddress,
                description = description
            )
        }

        companion object {
            fun newBuilder():BuildingDTO.BuildingDTOBuilder {
                return BuildingDTO.BuildingDTOBuilder()
            }

            fun newBuilder(src: BuildingDTO?): BuildingDTO.BuildingDTOBuilder {
                val builder = BuildingDTO.BuildingDTOBuilder()
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
