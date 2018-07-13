package basket.domain

import core.ddd.AggregateFactory

/**
 * @author Clément Garbay
 */
object BasketFactory : AggregateFactory<BasketAggregateId, Basket> {
    override fun build(initial: BasketAggregateId): Basket = Basket(initial)
}