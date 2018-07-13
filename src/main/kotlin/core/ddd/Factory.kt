package core.ddd

/**
 * @author Clément Garbay
 */
interface Factory<I, O> {
    fun build(initial: I): O
}