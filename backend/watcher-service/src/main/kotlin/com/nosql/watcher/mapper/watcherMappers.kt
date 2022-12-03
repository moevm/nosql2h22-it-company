package com.nosql.watcher.mapper

import com.nosql.watcher.entity.WatcherEntity
import com.nosql.watcher.entity.WatcherFillModel

fun WatcherEntity.merge(watcher: WatcherFillModel) {
    minutesAmount = watcher.minutesAmount
    comment = watcher.comment
}