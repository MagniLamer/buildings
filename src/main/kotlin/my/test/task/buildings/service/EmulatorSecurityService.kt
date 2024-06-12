package my.test.task.buildings.service

import my.test.task.buildings.domain.api.AbstractRequest

interface EmulatorSecurityService {

    fun isSignatureValid(request: AbstractRequest): Boolean
    fun buildSignature(externalId: String?): String
}