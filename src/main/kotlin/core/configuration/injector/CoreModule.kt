package core.configuration.injector

import com.github.salomonbrys.kodein.*
import core.command.CommandHandler
import core.command.CommandMiddleware
import core.event.EventHandler
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.bus.event.EventBus
import core.infrastructure.bus.query.QueryBus
import core.infrastructure.persistence.event.EventStore
import core.infrastructure.persistence.event.InMemoryEventStore
import core.infrastructure.resource.Api
import core.infrastructure.resource.Resource
import core.middleware.EventDispatcherMiddleware
import core.middleware.LoggerMiddleware
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
        bind() from setBinding<EventHandler<*>>()
        bind() from setBinding<CommandMiddleware>()
        bind() from setBinding<QueryMiddleware>()

        // Bind command and query bus to singleton instance
        bind<CommandBus>() with singleton { CommandBus(instance(), instance()) }
        bind<QueryBus>() with singleton { QueryBus(instance(), instance()) }
        bind<EventBus>() with singleton { EventBus(instance()) }

        // Bind default middlewares
        bind<CommandMiddleware>().inSet() with singleton { LoggerMiddleware() }
        bind<CommandMiddleware>().inSet() with singleton { EventDispatcherMiddleware(instance(), instance()) }
        bind<QueryMiddleware>().inSet() with singleton { LoggerMiddleware() }

        // Bind a default in memory EventStore (could be override)
        bind<EventStore>() with singleton { InMemoryEventStore() }

        // Prepare set binding for REST resources
        bind() from setBinding<Resource>()

        // Bind API to singleton instance
        bind<Api>() with singleton { Api(instance()) }
    }

}

