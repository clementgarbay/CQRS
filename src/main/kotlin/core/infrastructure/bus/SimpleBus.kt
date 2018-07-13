package core.infrastructure.bus

import core.Handler
import core.Message

/**
 * Bus with multiple message handlers, without middleware and result.
 *
 * @author Clément Garbay
 */
abstract class SimpleBus(private val handlers: Set<Handler<*, *>>) {

    @Suppress("UNCHECKED_CAST")
    fun <R> dispatch(message: Message<R>) {
        val matchingHandlers = handlers.filter { it.listenTo() == message::class }
                .map { handler -> handler as? Handler<Message<R>, R> }

        matchingHandlers.forEach { handler -> handler?.handle(message) }
    }

}