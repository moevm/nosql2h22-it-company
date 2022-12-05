package com.nosql.personservice.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.nosql.personservice.component.OfficeComponent
import com.nosql.personservice.component.PersonComponent
import com.nosql.personservice.component.ProjectComponent
import com.nosql.personservice.dto.export_import.office.ExportOfficeDto
import com.nosql.personservice.dto.export_import.person.ExportPersonDto
import com.nosql.personservice.dto.export_import.project.ExportProjectDto
import com.nosql.personservice.entity.OfficeEntity
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.entity.ProjectEntity
import com.nosql.personservice.service.ExportService
import com.nosql.personservice.util.convert
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
    private val personComponent: PersonComponent,
    private val projectComponent: ProjectComponent,
    private val officeComponent: OfficeComponent,
): ExportService {

    override suspend fun exportPeople(page: Int): ByteArrayResource  {
        var people: List<PersonEntity>
        var exportPage = page

        val writeFile = Paths.get(PEOPLE_FILE_NAME).toFile()
        val fileWriter = PrintWriter(BufferedWriter(withContext(IO) {
            FileWriter(writeFile, true)
        }))

        val seqWriter = mapper.writer().writeValuesAsArray(fileWriter)
        do {
            people = personComponent.getAll(PageRequest.of(exportPage, BATCH_SIZE))
            people
                .map { conversionService.convert(it, ExportPersonDto::class) }
                .forEach { seqWriter.write(it) }
            exportPage++
        } while (people.isNotEmpty())
        seqWriter.close()


        return ByteArrayResource(writeFile.readBytes())
            .also { writeFile.delete() }
    }

    override suspend fun exportProjects(page: Int): ByteArrayResource  {
        var projects: List<ProjectEntity>
        var exportPage = page

        val writeFile = Paths.get(PROJECTS_FILE_NAME).toFile()
        val fileWriter = PrintWriter(BufferedWriter(withContext(IO) {
            FileWriter(writeFile, true)
        }))

        val seqWriter = mapper.writer().writeValuesAsArray(fileWriter)
        do {
            projects = projectComponent.getAll(PageRequest.of(exportPage, BATCH_SIZE))
            projects
                .map { conversionService.convert(it, ExportProjectDto::class) }
                .forEach { seqWriter.write(it) }
            exportPage++
        } while (projects.isNotEmpty())
        seqWriter.close()

        return ByteArrayResource(writeFile.readBytes())
            .also { writeFile.delete() }
    }

    override suspend fun exportOffices(page: Int): ByteArrayResource {
        var offices: List<OfficeEntity>
        var exportPage = page

        val writeFile = Paths.get(OFFICES_FILE_NAME).toFile()
        val fileWriter = PrintWriter(BufferedWriter(withContext(IO) {
            FileWriter(writeFile, true)
        }))

        val seqWriter = mapper.writer().writeValuesAsArray(fileWriter)
        do {
            offices = officeComponent.getAll(PageRequest.of(exportPage, BATCH_SIZE))
            offices
                .map { conversionService.convert(it, ExportOfficeDto::class) }
                .forEach { seqWriter.write(it) }
            exportPage++
        } while (offices.isNotEmpty())
        seqWriter.close()

        return ByteArrayResource(writeFile.readBytes())
            .also { writeFile.delete() }
    }

    companion object {
        private const val PEOPLE_FILE_NAME = "people.json"
        private const val PROJECTS_FILE_NAME = "projects.json"
        private const val OFFICES_FILE_NAME = "offices.json"
        private const val BATCH_SIZE = 200
        private val mapper = ObjectMapper().enable(INDENT_OUTPUT)
    }
}