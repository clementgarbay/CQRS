package basket.query

import core.query.Query
import basket.domain.BasketViewModel

/**
 * @author Clément Garbay
 */
data class GetBasket(val id: String) : Query<BasketViewModel>