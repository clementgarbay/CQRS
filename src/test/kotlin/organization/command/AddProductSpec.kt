package organization.command

import basket.command.AddProduct
import basket.command.handler.AddProductHandler
import basket.infrastructure.repository.BasketRepository
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.bus.event.EventBus
import core.infrastructure.persistence.event.InMemoryEventStore
import core.middleware.EventDispatcherMiddleware
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

/**
 * @author Clément Garbay
 */
@RunWith(JUnitPlatform::class)
object AddProductSpec: Spek({

    given("an AddProductHandler and a command bus") {
        val eventStore = InMemoryEventStore()
        val middlewares = setOf(EventDispatcherMiddleware(EventBus(emptySet()), eventStore))
        val repository = BasketRepository(eventStore)
        val handlers = setOf(AddProductHandler(repository))

        val commandBus = CommandBus(handlers, middlewares)

        val productName = "myproduct"

        on("dispatch two AddProduct commands") {
            commandBus.dispatch(AddProduct("mybasket", productName, 10))
            commandBus.dispatch(AddProduct("mybasket", productName, 5))

            it("should have two events in eventstore") {
                eventStore.getStore().toList().size shouldEqual 2
                eventStore.getStore().toList().map { it.name() } shouldContain productName
            }
        }
    }

})
