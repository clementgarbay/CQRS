package core.middleware

import core.Message
import core.Middleware
import core.Result
import core.command.CommandMiddleware
import core.query.QueryMiddleware
import java.util.logging.Logger

/**
 * @author Clément Garbay
 */
class LoggerMiddleware : CommandMiddleware, QueryMiddleware {

    private val logger = Logger.getLogger(this::class.simpleName)

    override fun <R> intercept(message: Message<R>, next: () -> Result<R>?): Result<R> {
        logger.info("Message ${message::class.simpleName} sent")

        val startTime = System.currentTimeMillis()
        val response = Middleware.next(this, next)
        val endTime = System.currentTimeMillis() - startTime

        logger.info("Message ${message::class.simpleName} took: ${endTime}ms")

        return response
    }

}