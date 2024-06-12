package my.test.task.buildings.domain.entity

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
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID? = null,
    val name: String? = null,

    @OneToOne(mappedBy = "building", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val address: BuildingAddressEntity? = null,
    val description: String? = null
)
