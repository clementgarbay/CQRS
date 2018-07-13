package basket.command.handler

import basket.command.AddProduct
import basket.domain.Basket
import basket.domain.BasketAggregateId
import basket.infrastructure.repository.BasketRepository
import core.Result
import core.command.CommandHandler
import core.exception.LogicException
import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
class AddProductHandler(
        private val basketRepository: BasketRepository
) : CommandHandler<AddProduct, Basket> {

    override fun handle(message: AddProduct): Result<Basket> {
        val basket = basketRepository.get(BasketAggregateId(message.basketId))
            ?: throw LogicException("Cannot find basket with id ${message.basketId}")

        return basket.addProduct(message.name, message.quantity)
    }

    override fun listenTo(): KClass<AddProduct> = AddProduct::class

}