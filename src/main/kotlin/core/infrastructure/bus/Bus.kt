package core.infrastructure.bus

import core.Handler
import core.Message
import core.Middleware
import core.Result
import core.middleware.InvokeHandlerMiddleware

/**
 * Bus with only one message handler and middlewares.
 *
 * @author Clément Garbay
 */
abstract class Bus(handlers: Set<Handler<*, *>>, middlewares: Set<Middleware>) {

    private val middlewaresChain: Chain = middlewares.toList().foldRight(finalChain(handlers)) {
        commandMiddleware, chain -> Chain(commandMiddleware, chain)
    }

    fun <R> dispatch(message: Message<R>): Result<R> = middlewaresChain.apply(message)

    companion object {
        fun finalChain(handlers: Set<Handler<*, *>>) = Chain(InvokeHandlerMiddleware(handlers))
    }

}




