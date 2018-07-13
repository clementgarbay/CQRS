package core.infrastructure.persistence.event

import core.ddd.AggregateId
import core.event.Event
import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
interface EventStore {
    fun save(event: Event): Boolean
    fun getEvents(aggregateId: AggregateId): Sequence<Event>
    fun getEventsUntil(aggregateId: AggregateId, lastAppliedDate: ZonedDateTime): Sequence<Event>
}