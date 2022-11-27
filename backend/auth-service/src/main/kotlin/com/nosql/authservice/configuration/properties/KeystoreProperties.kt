package com.nosql.authservice.configuration.properties

import javax.validation.constraints.NotBlank

data class KeystoreProperties(

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val resourcePath: String,

    @field:NotBlank
    val keypairAlias: String,
)
