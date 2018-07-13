package core

import core.event.Event

/**
 * @author Clément Garbay
 */
data class Result<out R>(val result: R, val events: Set<Event> = emptySet())