package core.exception

import core.Message

/**
 * @author Clément Garbay
 */
class HandlerNotFoundException(messageToHandle: Message<*>) :
        Exception("Cannot find handler for message `${messageToHandle::class.simpleName}`")

