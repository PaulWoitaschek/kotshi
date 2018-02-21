package se.ansman.kotshi

import com.squareup.moshi.JsonQualifier

@JsonQualifier
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class WrappedInObject

@JsonQualifier
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class WrappedInArray

@JsonSerializable
data class MultipleJsonQualifiers(@WrappedInObject @WrappedInArray val string: String)
