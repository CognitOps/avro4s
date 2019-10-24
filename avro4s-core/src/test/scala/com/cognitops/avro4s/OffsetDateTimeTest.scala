package com.cognitops.avro4s

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import com.sksamuel.avro4s.{AvroSchema, Decoder, DefaultFieldMapper, Encoder, SchemaFor}
import org.scalatest.{Matchers, WordSpec}


class OffsetDateTimeTest extends WordSpec with Matchers {

  "OffsetDateTime" must {

    import com.cognitops.avro4s.CustomSchemaFor._
    import com.cognitops.avro4s.CustomEncoder._
    import com.cognitops.avro4s.CustomDecoder._

    val NOW = OffsetDateTime.now()
    val MAX = OffsetDateTime.MAX
    val MIN = OffsetDateTime.MIN

    "generate a schema with a logical type backed by a string" in {
      val schema = AvroSchema[OffsetDateTime]
      val expected = new org.apache.avro.Schema.Parser().parse(this.getClass.getResourceAsStream("/cognitops/offsetdatetime_schema.json"))
      schema shouldBe expected
    }

    "encode to an iso formatted String" in {
      def testEncode(datetime: OffsetDateTime): Unit = {
        val encoded = Encoder[OffsetDateTime].encode(
          datetime,
          AvroSchema[OffsetDateTime],
          DefaultFieldMapper
        )
        encoded shouldBe datetime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      }
      testEncode(NOW)
      testEncode(MAX)
      testEncode(MIN)
    }

    "decode an iso formatted String to an equivalent OffsetDatetime object" in {
      def testDecode(datetime: OffsetDateTime): Unit = {
        val dateTimeString = datetime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val decoder = Decoder[OffsetDateTime].decode(
          dateTimeString,
          AvroSchema[OffsetDateTime],
          DefaultFieldMapper
        )
        decoder shouldBe datetime
      }
      testDecode(NOW)
      testDecode(MAX)
      testDecode(MIN)
    }

    "round trip encode and decode into an equivalent object" in {
      def testRoundTrip(datetime: OffsetDateTime): Unit = {
        val encoded = Encoder[OffsetDateTime].encode(
          datetime,
          AvroSchema[OffsetDateTime],
          DefaultFieldMapper
        )
        val decoded = Decoder[OffsetDateTime].decode(
          encoded,
          AvroSchema[OffsetDateTime],
          DefaultFieldMapper
        )
        decoded shouldBe datetime
      }
      testRoundTrip(NOW)
      testRoundTrip(MAX)
      testRoundTrip(MIN)
    }

  }

}
