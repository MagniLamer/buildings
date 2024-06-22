package my.test.task.buildings.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@Builder
@Table(name = "buildings", schema = "building")
data class BuildingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty("buildingId")
    val id: UUID? = null,
    @JsonProperty("buildingName")
    val name: String? = null,
    val street: String? = null,
    val number: Int? = null,
    val postalCode: String? = null,
    val city: String? = null,
    val country: String? = null,
    val coordinate_x: String? = null,
    val coordinate_y: String? = null,
    val description: String? = null
)
