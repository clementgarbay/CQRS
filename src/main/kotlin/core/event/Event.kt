package core.event

import core.Message
import core.ddd.AggregateId
import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
interface Event : Message<Unit> {

    val aggregateId: AggregateId
    val appliedAt: ZonedDateTime
    val createdAt: ZonedDateTime

    fun name(): String = this::class.simpleName!!

}