package core.infrastructure.persistence.event

import core.ddd.AggregateId
import core.event.Event
import core.infrastructure.type.isEqualOrBefore
import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
class InMemoryEventStore : EventStore {

    private var eventStore: Sequence<Event> = emptySequence()

    override fun save(event: Event): Boolean {
        eventStore += event
        return true
    }

    override fun getEvents(aggregateId: AggregateId): Sequence<Event> {
        return eventStore
                .filter { it.aggregateId == aggregateId }
                .sortedBy { it.createdAt }
    }

    override fun getEventsUntil(aggregateId: AggregateId, lastAppliedDate: ZonedDateTime): Sequence<Event> {
        return eventStore
                .filter { it.aggregateId == aggregateId && it.appliedAt.isEqualOrBefore(lastAppliedDate) }
                .sortedBy { it.createdAt }
    }

    fun getStore() = eventStore
}