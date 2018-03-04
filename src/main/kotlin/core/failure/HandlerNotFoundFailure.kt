package core.failure

import core.Message

/**
 * @author Clément Garbay
 */
class HandlerNotFoundFailure<R>(val message: Message<R>) : Failure()