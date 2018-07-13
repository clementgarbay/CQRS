package core.middleware

import core.Message
import core.Middleware
import core.Result
import core.command.CommandMiddleware
import core.infrastructure.bus.event.EventBus
import core.infrastructure.persistence.event.EventStore

/**
 * @author Clément Garbay
 */
class EventDispatcherMiddleware(
        private val eventBus: EventBus,
        private val eventStore: EventStore
) : CommandMiddleware {

    override fun <R> intercept(message: Message<R>, next: () -> Result<R>?): Result<R> {
        val commandResponse = Middleware.next(this, next)

        // Dispatch events created by command handler
        commandResponse.events.forEach { event ->
            // Store event in eventstore
            eventStore.save(event)
            // Dispatch event to event bus
            eventBus.dispatch(event)
        }

        return commandResponse
    }

}