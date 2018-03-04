package core.middleware

import core.Message
import core.Middleware
import core.command.CommandMiddleware
import core.failure.Failure
import core.failure.ValidationFailure
import core.infrastructure.type.Either
import core.infrastructure.type.Left
import core.infrastructure.type.Right
import core.infrastructure.type.flatMap
import core.query.QueryMiddleware
import javax.validation.Validator

/**
 * @author Clément Garbay
 */
class ValidatorMiddleware(private val validator: Validator) : CommandMiddleware, QueryMiddleware {

    override fun <R> intercept(message: Message<R>, next: () -> Either<Failure, R>?): Either<Failure, R> =
        validate(message).flatMap { _ -> Middleware.next(this, next) }

    fun <R> validate(message: Message<R>): Either<ValidationFailure, Message<R>> {
        val violations = validator.validate(message)

        if (violations.isNotEmpty())
            return Left(ValidationFailure(violations.map { it.message }))

        return Right(message)
    }

}

