package core.query

import core.Bus

/**
 * @author Clément Garbay
 */
class QueryBus(override val handlers: List<QueryHandler<*, *>>) : Bus()
