package core.infrastructure.bus.command

import core.command.CommandHandler
import core.command.CommandMiddleware
import core.infrastructure.bus.Bus

/**
 * @author Clément Garbay
 */
class CommandBus(
    handlers: Set<CommandHandler<*, *>>,
    middlewares: Set<CommandMiddleware> = emptySet()
) : Bus(handlers, middlewares)
