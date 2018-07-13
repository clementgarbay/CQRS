package core.middleware

import core.Handler
import core.Message
import core.Middleware
import core.Result
import core.exception.HandlerNotFoundException

/**
 * @author Clément Garbay
 *
 * Note: this middleware doesn't call the `next` function because it is the last in the middleware chain.
 *
 * Message -> Middleware1 -> Middleware2 -> ... -> InvokeHandlerMiddleware
 */
class InvokeHandlerMiddleware(private val handlers: Set<Handler<*, *>>) : Middleware {

    @Suppress("UNCHECKED_CAST")
    override fun <R> intercept(message: Message<R>, next: () -> Result<R>?): Result<R> {
        val handler = (handlers.find { it.listenTo() == message::class }) as? Handler<Message<R>, R>?

        if (handler != null) {
            return handler.handle(message)
        }

        throw HandlerNotFoundException(message)
    }

}