package com.cognitops.avro4s

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import com.sksamuel.avro4s.{Encoder, FieldMapper}
import org.apache.avro.Schema

/**
 * Custom Cognitops Encoders
 */
object CustomEncoder {

  implicit object OffsetDateTimeEncoder extends Encoder[OffsetDateTime] {
    override def encode(value: OffsetDateTime, schema: Schema, fieldMapper: FieldMapper) =
      value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
  }

}
