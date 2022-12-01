package com.nosql.document.mapper

import com.nosql.document.entity.DocumentEntity

fun DocumentEntity.merge(document: DocumentEntity) {
    status = document.status
}
