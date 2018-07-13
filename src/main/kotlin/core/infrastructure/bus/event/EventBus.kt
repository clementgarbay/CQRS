package core.infrastructure.bus.event

import core.event.EventHandler
import core.infrastructure.bus.SimpleBus

/**
 * @author Clément Garbay
 */
class EventBus(handlers: Set<EventHandler<*>>) : SimpleBus(handlers)