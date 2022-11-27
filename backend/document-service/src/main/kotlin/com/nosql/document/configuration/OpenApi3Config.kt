package com.nosql.document.configuration

import com.nosql.document.constants.openapi.BEARER_FORMAT
import com.nosql.document.constants.openapi.BEARER_SCHEME
import com.nosql.document.constants.openapi.OPEN_API_TITLE
import com.nosql.document.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

@Configuration
@SecurityScheme(
    name = SECURITY_SCHEME_IDENTIFIER,
    type = SecuritySchemeType.HTTP,
    `in` = SecuritySchemeIn.HEADER,
    paramName = HttpHeaders.AUTHORIZATION,
    scheme = BEARER_SCHEME,
    bearerFormat = BEARER_FORMAT,
)
@OpenAPIDefinition(info = Info(title = OPEN_API_TITLE))
class OpenApi3Config
