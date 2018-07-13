package core.infrastructure.bus

import core.Message
import core.Middleware
import core.Result

/**
 * @author Clément Garbay
 */
class Chain(private val currentMiddleware: Middleware, val next: Chain? = null) {

    fun <R> apply(message: Message<R>): Result<R> =
        currentMiddleware.intercept(message) { next?.apply(message) }

}
