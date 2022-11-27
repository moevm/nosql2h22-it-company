package com.nosql.document.util

import org.springframework.core.convert.ConversionService
import kotlin.reflect.KClass

fun <T : Any> ConversionService.convert(source: Any, targetType: KClass<T>): T {
    return this.convert(source, targetType.java)!!
}
