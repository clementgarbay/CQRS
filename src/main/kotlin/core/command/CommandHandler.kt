package core.command

import core.Handler

/**
 * @author Clément Garbay
 */
interface CommandHandler<C : Command<R>, R> : Handler<C, R>
