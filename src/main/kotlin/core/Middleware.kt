package core

/**
 * @author Clément Garbay
 */
interface Middleware {
    fun <R> intercept(message: Message<R>, next: () -> R?): R?
}