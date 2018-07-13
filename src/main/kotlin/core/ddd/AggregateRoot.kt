package core.ddd

import core.event.Event
import core.event.EventApplier

/**
 * @author Clément Garbay
 */
interface AggregateRoot<out I: AggregateId, out T> {
    val id: I
    val applier: EventApplier<T>
    fun apply(event: Event): T = applier.apply(event)
}