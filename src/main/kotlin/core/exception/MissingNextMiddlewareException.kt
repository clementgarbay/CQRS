package core.exception

import core.Middleware

/**
 * @author Clément Garbay
 */
class MissingNextMiddlewareException(currentMiddleware: Middleware) :
        Exception("Missing next middleware after ${currentMiddleware::class.simpleName}")