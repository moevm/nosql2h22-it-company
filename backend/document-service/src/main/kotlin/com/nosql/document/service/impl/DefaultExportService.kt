package com.nosql.document.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.nosql.document.component.DocumentComponent
import com.nosql.document.dto.ExportDocumentDto
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.service.ExportService
import com.nosql.document.util.convert
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
    private val documentComponent: DocumentComponent,
): ExportService {

    override suspend fun exportDocuments(page: Int): ByteArrayResource {
        var documents: List<DocumentEntity>
        var exportPage = page

        val writeFile = Paths.get(FILE_NAME).toFile()
        val fileWriter = PrintWriter(BufferedWriter(withContext(IO) {
            FileWriter(writeFile, true)
        }))

        val seqWriter = mapper.writer().writeValuesAsArray(fileWriter)
        do {
            documents = documentComponent.getAll(PageRequest.of(exportPage, BATCH_SIZE))
            documents
                .map { conversionService.convert(it, ExportDocumentDto::class) }
                .forEach { seqWriter.write(it) }
            exportPage++
        } while (documents.isNotEmpty())
        seqWriter.close()

        return ByteArrayResource(writeFile.readBytes())
            .also { writeFile.delete() }
    }

    companion object {
        private const val FILE_NAME = "documents.json"
        private const val BATCH_SIZE = 200
        private val mapper = ObjectMapper().enable(INDENT_OUTPUT)
    }
}