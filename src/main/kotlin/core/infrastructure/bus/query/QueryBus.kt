package core.infrastructure.bus.query

import core.infrastructure.bus.Bus
import core.query.QueryHandler
import core.query.QueryMiddleware

/**
 * @author Clément Garbay
 */
class QueryBus(
    handlers: Set<QueryHandler<*, *>>,
    middlewares: Set<QueryMiddleware> = emptySet()
) : Bus(handlers, middlewares)
