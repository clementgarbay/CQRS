package core.infrastructure.persistence

import core.ddd.AggregateId
import core.ddd.AggregateRoot
import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
interface Repository<I : AggregateId, T : AggregateRoot<I, T>> {
    fun get(id: I): T?
    fun get(id: I, at: ZonedDateTime): T?
}