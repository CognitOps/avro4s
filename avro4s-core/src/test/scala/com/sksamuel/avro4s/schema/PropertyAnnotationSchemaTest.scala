package com.sksamuel.avro4s.schema

import com.sksamuel.avro4s.AvroProp
import com.sksamuel.avro4s.internal.SchemaEncoder
import org.scalatest.{Matchers, WordSpec}

class PropertyAnnotationSchemaTest extends WordSpec with Matchers {
  "SchemaEncoder" should {
    "support prop annotation on class" in {
      @AvroProp("cold", "play") case class Annotated(str: String)
      val expected = new org.apache.avro.Schema.Parser().parse(getClass.getResourceAsStream("/props_annotation_class.avsc"))
      val schema = SchemaEncoder[Annotated].encode
      schema.toString(true) shouldBe expected.toString(true)
    }
    "support prop annotation on field" in {
      case class Annotated(@AvroProp("cold", "play") str: String, @AvroProp("kate", "bush") long: Long, int: Int)
      val expected = new org.apache.avro.Schema.Parser().parse(getClass.getResourceAsStream("/props_annotation_field.avsc"))
      val schema = SchemaEncoder[Annotated].encode
      schema.toString(true) shouldBe expected.toString(true)
    }
  }
}
