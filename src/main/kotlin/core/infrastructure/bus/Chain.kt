package core.infrastructure.bus

import core.Message
import core.Middleware

/**
 * @author Clément Garbay
 */
class Chain(private val currentMiddleware: Middleware, val next: Chain? = null) {

    fun <R> apply(message: Message<R>): R? {
        return currentMiddleware.intercept(message) { next?.apply(message) }
    }

}
