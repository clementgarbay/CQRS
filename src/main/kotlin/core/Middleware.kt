package core

import core.failure.Failure
import core.failure.MissingNextMiddlewareFailure
import core.infrastructure.type.Either
import core.infrastructure.type.Left

/**
 * @author Clément Garbay
 */
interface Middleware {
    fun <R> intercept(message: Message<R>, next: () -> Either<Failure, R>?): Either<Failure, R>

    companion object {
        // Helper to safely call next middleware
        fun <R> next(currentMiddleware: Middleware, next: () -> Either<Failure, R>?): Either<Failure, R> {
            return next() ?: Left(MissingNextMiddlewareFailure(currentMiddleware))
        }
    }
}

