package core.event

import core.Handler

/**
 * @author Clément Garbay
 */
interface EventHandler<E : Event> : Handler<E, Unit>