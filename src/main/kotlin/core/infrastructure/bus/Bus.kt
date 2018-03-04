package core.infrastructure.bus

import core.Handler
import core.Message
import core.Middleware
import core.failure.Failure
import core.infrastructure.type.Either
import core.middleware.InvokeHandlerMiddleware

/**
 * @author Clément Garbay
 */
abstract class Bus(handlers: List<Handler<*, *>>, middlewares: List<Middleware>) {

    private val middlewaresChain: Chain = middlewares.foldRight(finalChain(handlers), {
        commandMiddleware, chain -> Chain(commandMiddleware, chain.next)
    })

    fun <R> dispatch(message: Message<R>): Either<Failure, R> = middlewaresChain.apply(message)

    companion object {
        fun finalChain(handlers: List<Handler<*, *>>) = Chain(InvokeHandlerMiddleware(handlers))
    }

}




