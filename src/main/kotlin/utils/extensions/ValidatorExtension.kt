package utils.extensions

import javax.validation.ValidationException
import javax.validation.Validator

fun <T> Validator.validateAndThrow(obj: T, vararg groups: Class<*>?) {
    val errors = this.validate(obj, *groups)
    if(errors.size > 0) {
        throw ValidationException(errors.joinToString { e -> "${e.propertyPath}: ${e.message}" })
    }
}