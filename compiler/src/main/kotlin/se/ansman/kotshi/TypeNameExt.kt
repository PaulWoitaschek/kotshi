package se.ansman.kotshi

import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeVariableName
import com.squareup.javapoet.WildcardTypeName

val TYPE_NAME_STRING = TypeName.get(String::class.java)

val TypeName.jvmDefault: String
    get() {
        if (!isPrimitive) return "null"
        return when (this) {
            TypeName.BOOLEAN -> "false"
            TypeName.BYTE -> "0"
            TypeName.SHORT -> "0"
            TypeName.INT -> "0"
            TypeName.LONG -> "0"
            TypeName.CHAR -> "0"
            TypeName.FLOAT -> "0f"
            TypeName.DOUBLE -> "0.0"
            else -> throw AssertionError("Unknown type $this")
        }
    }

val TypeName.rawType: TypeName
    get() = when (this) {
        is ParameterizedTypeName -> this.rawType
        else -> this
    }

fun TypeName.containsUnboundWildcards(): Boolean =
        when (this) {
            is ParameterizedTypeName -> typeArguments.any { it.containsUnboundWildcards() }
            is WildcardTypeName -> when {
                lowerBounds.size == 1 -> lowerBounds[0].containsUnboundWildcards()
                upperBounds[0] == TypeName.OBJECT -> true
                else -> upperBounds[0].containsUnboundWildcards()
            }
            is TypeVariableName -> false
            else -> false
        }