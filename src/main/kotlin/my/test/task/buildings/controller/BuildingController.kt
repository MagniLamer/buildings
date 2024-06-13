package my.test.task.buildings.controller

import lombok.extern.slf4j.Slf4j
import my.test.task.buildings.annotation.CheckSignature
import my.test.task.buildings.annotation.LogRequest
import my.test.task.buildings.domain.api.BuildingRequest
import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.handler.BuildingRequestHandler
import org.springframework.http.MediaType
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
    @GetMapping("/{id}")
    fun getBuildingById(@PathVariable(name = "id") buildingId: String, model: Model): String {
        return buildingRequestHandler.getBuildingById(buildingId, model);
    }

    @LogRequest
    @PostMapping("/")
    fun saveBuildings(@RequestBody request: Map<String, String>, model: Model): String =
        buildingRequestHandler.saveBuilding(request, model)

    @LogRequest
    @PutMapping("/")
    fun updateBuildings(@RequestBody request: Map<String, String>, model: Model): String =
        buildingRequestHandler.updateBuilding(request, model)

    @LogRequest
    @DeleteMapping("/{id}")
    fun deleteReadRecords(@PathVariable(name = "id") buildingId: String, model: Model): String =
        buildingRequestHandler.deleteBuilding(buildingId, model)
}