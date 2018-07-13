package core.infrastructure.persistence

import core.ddd.AggregateFactory
import core.ddd.AggregateId
import core.ddd.AggregateRoot
import core.infrastructure.persistence.event.EventStore
import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
open class SimpleRepository<I : AggregateId, T : AggregateRoot<I, T>>(
        private val eventStore: EventStore,
        private val aggregateFactory: AggregateFactory<I, T>
) : Repository<I, T> {

    override fun get(id: I): T? {
        return eventStore.getEvents(id)
                .fold(aggregateFactory.build(id)) {
                    partialAggregate, event -> partialAggregate.apply(event)
                }
    }

    override fun get(id: I, at: ZonedDateTime): T? {
        return eventStore.getEventsUntil(id, at)
                .fold(aggregateFactory.build(id)) {
                    partialAggregate, event -> partialAggregate.apply(event)
                }
    }

}