package basket.domain

import basket.event.ProductAdded
import basket.event.ProductRemoved
import core.event.EventApplier
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * @author Clément Garbay
 */
class BasketEventApplier(private val basket: Basket) : EventApplier<Basket>(basket) {

    override val eventsAppliers: Map<KClass<*>, KFunction<Basket>> = mapOf(
            ProductAdded::class to this::productAdded,
            ProductRemoved::class to this::productRemoved
    )

    fun productAdded(event: ProductAdded): Basket {
        val totalQuantity = (basket.products[event.name] ?: 0) + event.quantity
        return basket.copy(products = basket.products + (event.name to totalQuantity))
    }

    fun productRemoved(event: ProductRemoved): Basket {
        val totalQuantity = (basket.products[event.name] ?: 0) - event.quantity
        val finalQuantity = if (totalQuantity > 0) totalQuantity else 0
        return basket.copy(products = basket.products + (event.name to finalQuantity))
    }

}