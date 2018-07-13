package basket.event

import core.ddd.AggregateId
import core.event.Event
import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
data class ProductRemoved(
        override val aggregateId: AggregateId,
        val name: String,
        val quantity: Int
) : Event {

    override val appliedAt: ZonedDateTime = ZonedDateTime.now()
    override val createdAt: ZonedDateTime = ZonedDateTime.now()

}