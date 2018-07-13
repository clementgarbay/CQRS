package core

import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
interface Handler<M : Message<R>, R> {
    fun handle(message: M): Result<R>
    fun listenTo(): KClass<M>
}