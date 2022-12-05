package com.nosql.watcher.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.nosql.watcher.component.WatcherComponent
import com.nosql.watcher.dto.ExportWatcherDto
import com.nosql.watcher.entity.WatcherEntity
import com.nosql.watcher.service.ExportService
import com.nosql.watcher.util.convert
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.springframework.core.convert.ConversionService
import org.springframework.core.io.ByteArrayResource
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Paths

@Service
class DefaultExportService(
    private val conversionService: ConversionService,
    private val watcherComponent: WatcherComponent,
): ExportService {

    override suspend fun exportWatchers(page: Int): ByteArrayResource {
        var watchers: List<WatcherEntity>
        var exportPage = page

        val writeFile = Paths.get(FILE_NAME).toFile()
        val fileWriter = PrintWriter(BufferedWriter(withContext(IO) {
            FileWriter(writeFile, true)
        }))

        val seqWriter = mapper.writer().writeValuesAsArray(fileWriter)
        do {
            watchers = watcherComponent.getAll(PageRequest.of(exportPage, BATCH_SIZE))
            watchers
                .map { conversionService.convert(it, ExportWatcherDto::class) }
                .forEach { seqWriter.write(it) }
            exportPage++
        } while (watchers.isNotEmpty())
        seqWriter.close()

        return ByteArrayResource(writeFile.readBytes())
            .also { writeFile.delete() }
    }

    companion object {
        private const val FILE_NAME = "watchers.json"
        private const val BATCH_SIZE = 200
        private val mapper = ObjectMapper().enable(INDENT_OUTPUT)
    }
}