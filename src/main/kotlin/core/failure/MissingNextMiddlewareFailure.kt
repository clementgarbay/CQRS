package core.failure

import core.Middleware

/**
 * @author Clément Garbay
 */
class MissingNextMiddlewareFailure(val currentMiddleware: Middleware) : Failure()