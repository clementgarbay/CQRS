package basket.domain

import core.ddd.AggregateId

/**
 * @author Clément Garbay
 */
data class BasketAggregateId(override val id: String) : AggregateId