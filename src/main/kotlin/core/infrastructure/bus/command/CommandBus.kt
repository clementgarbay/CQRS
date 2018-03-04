package core.infrastructure.bus.command

import core.command.CommandHandler
import core.command.CommandMiddleware
import core.infrastructure.bus.Bus

/**
 * @author Clément Garbay
 */
class CommandBus(
    handlers: List<CommandHandler<*, *>>,
    middlewares: List<CommandMiddleware> = emptyList()
) : Bus(handlers, middlewares)
