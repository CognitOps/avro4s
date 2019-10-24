package com.cognitops.avro4s

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import com.sksamuel.avro4s.{Decoder, FieldMapper}
import org.apache.avro.Schema

/**
 * Custom Cognitops Decoders
 */
object CustomDecoder {

  implicit object OffsetDateTimeDecoder extends Decoder[OffsetDateTime] {
    override def decode(value: Any, schema: Schema, fieldMapper: FieldMapper) =
      OffsetDateTime.parse(value.toString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
  }

}
