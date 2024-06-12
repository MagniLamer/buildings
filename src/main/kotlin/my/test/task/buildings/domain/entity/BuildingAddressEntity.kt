package my.test.task.buildings.domain.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import my.test.task.buildings.domain.model.BuildingCoordinates
import java.util.UUID

@Entity
@Table(name = "address", schema = "building")
data class BuildingAddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long? = null,
    val street: String? = null,
    val number: Int? = null,
    val postalCode: String? = null,
    val city: String? = null,
    val country: String? = null,
    val coordinate_x: String? = null,
    val coordinate_y: String? = null,

    @OneToOne
    @JoinColumn(name = "building_id")
    val building: BuildingEntity? = null
)