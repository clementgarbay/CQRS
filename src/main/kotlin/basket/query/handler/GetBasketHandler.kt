package basket.query.handler

import basket.domain.BasketAggregateId
import basket.domain.BasketViewModel
import basket.domain.Product
import basket.infrastructure.repository.BasketRepository
import basket.query.GetBasket
import core.Result
import core.exception.LogicException
import core.query.QueryHandler
import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
class GetBasketHandler(
        private val basketRepository: BasketRepository
) : QueryHandler<GetBasket, BasketViewModel> {

    override fun handle(message: GetBasket): Result<BasketViewModel> {
        val basket = basketRepository.get(BasketAggregateId(message.id))
            ?: throw LogicException("Cannot find basket with id ${message.id}")
        val products = basket.products.map { Product(it.key, it.value) }
        return Result(BasketViewModel(basket.id.id, products))
    }

    override fun listenTo(): KClass<GetBasket> = GetBasket::class

}