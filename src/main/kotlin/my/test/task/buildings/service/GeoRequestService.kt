package my.test.task.buildings.service

import my.test.task.buildings.domain.model.Building
import my.test.task.buildings.domain.model.ResponseDetails
import java.util.concurrent.CompletableFuture

interface GeoRequestService {
    fun performRequest(building: Building): CompletableFuture<ResponseDetails>
}