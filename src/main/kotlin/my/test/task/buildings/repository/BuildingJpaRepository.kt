package my.test.task.buildings.repository

import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

@Repository
public interface BuildingJpaRepository : JpaRepository<BuildingEntity,UUID>  {
    override fun findAll(pageable: Pageable): Page<BuildingEntity>
}