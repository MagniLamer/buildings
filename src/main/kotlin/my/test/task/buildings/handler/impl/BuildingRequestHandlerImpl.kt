package my.test.task.buildings.handler.impl

import jakarta.transaction.Transactional
import my.test.task.buildings.handler.BuildingRequestHandler
import my.test.task.buildings.mapper.BuildingConverter
import my.test.task.buildings.repository.BuildingJpaRepository
import my.test.task.buildings.service.CoordinateService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.ui.Model

internal const val BUILDING = "buildings"

@Service
class BuildingRequestHandlerImpl(
    private val buildingJpaRepository: BuildingJpaRepository,
    private val buildingConverter: BuildingConverter,
    private val coordinateService: CoordinateService
) : BuildingRequestHandler {

    @Transactional
    override fun saveBuilding(request: Map<String, String>, model: Model): String {
//        for (building in request.buildings) {
            val buildingWithCoordinates = coordinateService.buildBuildingWithCoordinates(request)
            val buildingEntity = buildingConverter.convertBuildingToEntity(buildingWithCoordinates)
            buildingJpaRepository.save(buildingEntity)
//        }
        return "success_adding"
    }

    override fun deleteBuilding(buildingId: String, model: Model): String {
        TODO("Not yet implemented")
    }

    override fun getBuildingById(buildingId: String, model: Model): String {
        TODO("Not yet implemented")
    }

    override fun getAllBuildings(model: Model): String {
        val firstPageWithTwentyRecords = PageRequest.of(0, 20)
        val allBuildings = buildingJpaRepository.findAll(firstPageWithTwentyRecords)
        model.addAttribute(BUILDING, allBuildings)
        return "main_page"
    }
//    @GetMapping("/payment-page")
//    fun getPaymentPage(@RequestParam request: Map<String, String>, model: Model): String {
//        model.addAttribute(TRANSACTION_ID_KEY, request[TRANSACTION_ID_KEY])
//        model.addAttribute(EXT_PATH, if (env == ENV_VALUE_UAT) EXT_PATH_VALUE else StringUtils.EMPTY)
//        return "payment-page"
//    }
//
//    @GetMapping("/payment-page-submit")
//    fun submit(@RequestParam request: Map<String, String>, model: Model): String {
//        logger.info("[EMULATOR SERVICE] Got payment page submit, payload: {}", request)
//        val redirectUrl = paymentPageHandler.handlePaymentPageResponse(request)
//        return "redirect:$redirectUrl"
//    }
}