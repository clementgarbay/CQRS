package core.middleware

import core.Handler
import core.Message
import core.Middleware

/**
 * @author Clément Garbay
 */
class InvokeHandlerMiddleware(private val handlers: List<Handler<*, *>>) : Middleware {

    @Suppress("UNCHECKED_CAST")
    override fun <R> intercept(message: Message<R>, next: () -> R?): R? =
        ((handlers.find { it.listenTo() == message::class }) as? Handler<Message<R>, R>?)?.handle(message)

}