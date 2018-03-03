package core

import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
interface Handler<M : Message<R>, R> {
    fun listenTo(): KClass<M>
    fun handle(message: M): R
}