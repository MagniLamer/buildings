package my.test.task.buildings.mapper.impl

import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.domain.model.Building
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

private const val OLD_NAME = "oldName"
private const val NEW_NAME = "newName"
private const val OLD_DESCRIPTION = "oldDescription"
private const val NEW_DESCRIPTION = "newDescription"

internal class BuildingConverterImplTest {
    private val buildingConverter = BuildingConverterImpl()

    @Test
    fun `should update building entity`() {
        //given
        val building = BuildingEntity(
            name = NEW_NAME,
            description= NEW_DESCRIPTION
        )
        val buildingEntity = BuildingEntity(
            name = OLD_NAME,
            description = OLD_DESCRIPTION
        )

        //when
        val actualResult = buildingConverter.updateBuildingEntity(building, buildingEntity)

        //then
        assertEquals(actualResult.name, NEW_NAME)
        assertEquals(actualResult.description, NEW_DESCRIPTION)
    }
}