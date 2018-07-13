package basket.infrastructure.repository

import basket.domain.Basket
import basket.domain.BasketAggregateId
import basket.domain.BasketFactory
import core.infrastructure.persistence.SimpleRepository
import core.infrastructure.persistence.event.EventStore

/**
 * @author Clément Garbay
 */
class BasketRepository(eventStore: EventStore) :
        SimpleRepository<BasketAggregateId, Basket>(eventStore, BasketFactory)