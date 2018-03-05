package core.configuration.injector

import com.github.salomonbrys.kodein.*
import core.command.CommandHandler
import core.command.CommandMiddleware
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.bus.query.QueryBus
import core.infrastructure.resource.Api
import core.infrastructure.resource.Resource
import core.query.QueryHandler
import core.query.QueryMiddleware

/**
 * @author Clément Garbay
 */
object CoreModule {

    val bindings = Kodein.Module {
        // Prepare all set binding for handlers and middlewares
        bind() from setBinding<CommandHandler<*, *>>()
        bind() from setBinding<QueryHandler<*, *>>()
        bind() from setBinding<CommandMiddleware>()
        bind() from setBinding<QueryMiddleware>()

        // Prepare set binding for REST resources
        bind() from setBinding<Resource>()

        // Bind API to singleton instance
        bind<Api>() with singleton { Api(instance()) }

        // Bind command and query bus to singleton instance
        bind<CommandBus>() with singleton { CommandBus(instance(), instance()) }
        bind<QueryBus>() with singleton { QueryBus(instance(), instance()) }
    }

}

