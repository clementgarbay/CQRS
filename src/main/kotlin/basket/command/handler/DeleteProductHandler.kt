package basket.command.handler

import core.Result
import core.command.CommandHandler
import core.exception.LogicException
import basket.command.DeleteProduct
import basket.domain.Basket
import basket.domain.BasketAggregateId
import basket.infrastructure.repository.BasketRepository
import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
class DeleteProductHandler(
        private val basketRepository: BasketRepository
) : CommandHandler<DeleteProduct, Basket> {

    override fun handle(message: DeleteProduct): Result<Basket> {
        val basket = basketRepository.get(BasketAggregateId(message.basketId))
            ?: throw LogicException("Cannot find basket with id ${message.basketId}")

        return basket.removeProduct(message.name, message.quantity)
    }

    override fun listenTo(): KClass<DeleteProduct> = DeleteProduct::class

}