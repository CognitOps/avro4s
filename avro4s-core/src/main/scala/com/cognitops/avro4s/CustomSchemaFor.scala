package com.cognitops.avro4s

import java.time.OffsetDateTime

import com.cognitops.avro4s.CustomLogicalTypes.OffsetDateTimeLogicalType
import com.sksamuel.avro4s.{FieldMapper, SchemaFor}
import org.apache.avro.SchemaBuilder

/**
 * Custom Cognitops schemas
 */
object CustomSchemaFor {

  implicit object OffsetDateTimeSchemaFor extends SchemaFor[OffsetDateTime] {
    override def schema(fieldMapper: FieldMapper) = OffsetDateTimeLogicalType.addToSchema(SchemaBuilder.builder().stringType())
  }

}
