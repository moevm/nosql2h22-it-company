package com.nosql.authservice.controller

import com.nosql.authservice.constants.url.PUBLIC_API_V1_AUTH_URL_PATH
import com.nosql.authservice.service.JwksService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PUBLIC_API_V1_AUTH_URL_PATH)
class JwksController(
    private val jwksService: JwksService,
) {

    @GetMapping(path = [GET_JWKS_URL_PATH], produces = [APPLICATION_JSON_VALUE])
    fun getJwks(): Map<String, Any> =
        jwksService.getJwks().toJSONObject()

    companion object {

        private const val GET_JWKS_URL_PATH = "/.well-known/jwks.json"
    }
}
