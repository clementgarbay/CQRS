package basket.configuration.injector

import com.github.salomonbrys.kodein.*
import core.command.CommandHandler
import core.infrastructure.resource.Resource
import core.query.QueryHandler
import basket.command.handler.AddProductHandler
import basket.command.handler.DeleteProductHandler
import basket.infrastructure.repository.BasketRepository
import basket.infrastructure.resource.BasketResource
import basket.query.handler.GetBasketHandler

/**
 * @author Clément Garbay
 */
object BasketModule {

    val bindings = Kodein.Module {
        // Bind BC handlers to application handlers set
        bind<CommandHandler<*, *>>().inSet() with singleton { AddProductHandler(instance()) }
        bind<CommandHandler<*, *>>().inSet() with singleton { DeleteProductHandler(instance()) }
        bind<QueryHandler<*, *>>().inSet() with singleton { GetBasketHandler(instance()) }

        // Bind basket resource to application resources set
        bind<Resource>().inSet() with singleton { BasketResource(instance(), instance()) }

        // Bind instance of basket repository
        bind<BasketRepository>() with singleton { BasketRepository(instance()) }
    }

}
