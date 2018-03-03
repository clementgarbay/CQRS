package core

/**
 * @author Clément Garbay
 */
abstract class Bus {
    abstract val handlers: List<Handler<*, *>>

    fun <M : Message<R>, R> dispatch(message: M): R? =
        ((handlers.find { it.listenTo() == message::class }) as? Handler<M, R>?)?.handle(message)
}