package core

import core.exception.MissingNextMiddlewareException

/**
 * @author Clément Garbay
 */
interface Middleware {
    fun <R> intercept(message: Message<R>, next: () -> Result<R>?): Result<R>

    companion object {
        fun <R> next(currentMiddleware: Middleware, next: () -> Result<R>?): Result<R> {
            return next() ?: throw MissingNextMiddlewareException(currentMiddleware)
        }
    }
}

