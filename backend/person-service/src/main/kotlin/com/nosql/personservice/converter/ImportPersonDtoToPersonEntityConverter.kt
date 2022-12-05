package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.entity.ContactsEntity
import com.nosql.personservice.entity.JobTimeEntity
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class ImportPersonDtoToPersonEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ImportPersonDto, PersonEntity> {

    override fun convert(source: ImportPersonDto) = PersonEntity(
        id = ObjectId(source.id!!),
        name = source.name!!,
        surname = source.surname!!,
        patronymic = source.patronymic!!,
        sex = SexEnum.get(source.sex!!),
        birthday = dateFormatter.parse(source.birthday!!),
        firstWorkDate = dateFormatter.parse(source.firstWorkDate!!),
        position = PositionEnum.get(source.position!!),
        status = StatusEnum.get(source.status!!),
        contacts = conversionService.convert(source.contacts!!, ContactsEntity::class),
        jobTime = conversionService.convert(source.jobTime!!, JobTimeEntity::class),
        officeId = ObjectId(source.officeId),
        confidential = conversionService.convert(source.confidential!!, ConfidentialEntity::class),
        comment = source.comment,
    )

    companion object {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }

}