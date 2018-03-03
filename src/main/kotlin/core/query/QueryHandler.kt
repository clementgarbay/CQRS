package core.query

import core.Handler

/**
 * @author Clément Garbay
 */
interface QueryHandler<Q : Query<R>, R> : Handler<Q, R>
