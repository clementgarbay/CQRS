package core.exception

import core.event.Event

/**
 * @author Clément Garbay
 */
class EventApplierNotFoundException(eventToApply: Event) :
        Exception("Cannot find applier for event `${eventToApply::class.simpleName}`")