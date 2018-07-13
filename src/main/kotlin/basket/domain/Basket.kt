package basket.domain

import core.Result
import core.ddd.AggregateRoot
import core.event.EventApplier
import basket.event.ProductAdded
import basket.event.ProductRemoved

/**
 * @author Clément Garbay
 */
data class Basket(
        override val id: BasketAggregateId,
        val products: Map<String, Int> = emptyMap()
) : AggregateRoot<BasketAggregateId, Basket> {

    override val applier: EventApplier<Basket> = BasketEventApplier(this)

    fun addProduct(name: String, quantity: Int): Result<Basket> {
        val event = ProductAdded(id, name, quantity)
        return Result(apply(event), setOf(event))
    }

    fun removeProduct(name: String, quantity: Int): Result<Basket> {
        val event = ProductRemoved(id, name, quantity)
        return Result(apply(event), setOf(event))
    }
}