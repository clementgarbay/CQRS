package core.middleware

import core.Handler
import core.Message
import core.Middleware
import core.failure.Failure
import core.failure.HandlerNotFoundFailure
import core.infrastructure.type.Either
import core.infrastructure.type.Left
import core.infrastructure.type.Right

/**
 * @author Clément Garbay
 *
 * Note: this middleware doesn't call the `next` function because it is the last in the middleware chain.
 *
 * Message -> Middleware1 -> Middleware2 -> ... -> InvokeHandlerMiddleware
 */
class InvokeHandlerMiddleware(private val handlers: List<Handler<*, *>>) : Middleware {

    @Suppress("UNCHECKED_CAST")
    override fun <R> intercept(message: Message<R>, next: () -> Either<Failure, R>?): Either<Failure, R> {
        val handler = (handlers.find { it.listenTo() == message::class }) as? Handler<Message<R>, R>?

        if (handler != null) {
            return Right(handler.handle(message))
        }

        return Left(HandlerNotFoundFailure(message))
    }

}