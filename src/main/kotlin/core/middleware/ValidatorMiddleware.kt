package core.middleware

import core.Message
import core.command.CommandMiddleware
import core.exception.ValidationException
import core.query.QueryMiddleware
import javax.validation.Validator

/**
 * @author Clément Garbay
 */
class ValidatorMiddleware(private val validator: Validator) : CommandMiddleware, QueryMiddleware {

    override fun <R> intercept(message: Message<R>, next: () -> R?): R? {
        validate(message)
        return next()
    }

    fun <R> validate(message: Message<R>) {
        val violations = validator.validate(message)

        if (violations.isNotEmpty())
            throw ValidationException(violations.map { it.message })
    }

}

