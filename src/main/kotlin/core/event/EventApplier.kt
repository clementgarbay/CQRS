package core.event

import core.exception.EventApplierNotFoundException
import core.infrastructure.type.isEqualOrBeforeNow
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * @author Clément Garbay
 */
abstract class EventApplier<out T>(private val root: T) {

    protected abstract val eventsAppliers: Map<KClass<*>, KFunction<T>>

    fun apply(event: Event): T {
        val applier = eventsAppliers[event::class] ?: throw EventApplierNotFoundException(event)

        if (event.appliedAt.isEqualOrBeforeNow())
            return applier.call(event)

        return root
    }

}