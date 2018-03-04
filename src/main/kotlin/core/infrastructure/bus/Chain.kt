package core.infrastructure.bus

import core.Message
import core.Middleware
import core.failure.Failure
import core.infrastructure.type.Either

/**
 * @author Clément Garbay
 */
class Chain(private val currentMiddleware: Middleware, val next: Chain? = null) {

    fun <R> apply(message: Message<R>): Either<Failure, R> =
        currentMiddleware.intercept(message) { next?.apply(message) }

}
