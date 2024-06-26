package my.test.task.buildings.controller

import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.annotation.LogRequest
import my.test.task.buildings.domain.api.BuildingFilterRequest
import my.test.task.buildings.domain.api.BuildingRequest
import my.test.task.buildings.domain.entity.BuildingEntity
import my.test.task.buildings.handler.BuildingRequestHandler
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Slf4j
@Controller
@RequestMapping(
    path = ["/public/api/buildings"]
)
class BuildingController(
    private val buildingRequestHandler: BuildingRequestHandler
) {

    @GetMapping("/add")
    fun addBuilding(model: Model): String = "add"

    @GetMapping("/delete")
    fun delete(model: Model): String = "delete"

    @GetMapping("/update")
    fun update(model: Model): String = "update"

    @LogRequest
    @GetMapping("/")
    fun getAllBuildings(model: Model): String =
        buildingRequestHandler.getAllBuildings(model)


    @LogRequest
    @PostMapping("/")
    fun saveBuildings(@RequestBody request: BuildingRequest, model: Model): String =
        buildingRequestHandler.saveBuilding(request, model)

    @LogRequest
    @PutMapping("/")
    fun updateBuildings(@RequestBody buildingEntity: BuildingEntity, model: Model): String =
        buildingRequestHandler.updateBuilding(buildingEntity, model)

    @LogRequest
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteReadRecords(@PathVariable(name = "id") buildingId: String, model: Model): String =
        buildingRequestHandler.deleteBuilding(buildingId, model)

    @LogRequest
    @PostMapping("/filter")
    fun filterBuildings(@RequestBody filterRequest: BuildingFilterRequest): ResponseEntity<List<BuildingEntity>>? {
        val buildings = buildingRequestHandler.filterBuildings(filterRequest)
        return ResponseEntity.ok(buildings)
    }
}