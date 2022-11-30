package com.nosql.watcher.component

import org.bson.types.ObjectId

interface ProjectComponent {

    suspend fun getIdByName(name: String): ObjectId
}