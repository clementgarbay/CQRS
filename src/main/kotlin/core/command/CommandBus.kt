package core.command

import core.Bus

/**
 * @author Clément Garbay
 */
class CommandBus(override val handlers: List<CommandHandler<*, *>>) : Bus()
