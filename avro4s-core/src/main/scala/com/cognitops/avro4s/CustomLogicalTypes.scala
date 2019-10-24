package com.cognitops.avro4s

import org.apache.avro.{LogicalType, Schema}

/**
 * Custom Cognitops LogicalTypes
 */
object CustomLogicalTypes {

  implicit object OffsetDateTimeLogicalType extends LogicalType("iso-datetime with offset") {

    override def validate(schema: Schema): Unit = {
      super.validate(schema)
      if (schema.getType != Schema.Type.STRING) {
        throw new IllegalArgumentException("Logical type iso-datetime with offset must be backed by String")
      }
    }

  }

}


