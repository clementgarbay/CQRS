package organization.command

import core.failure.HandlerNotFoundFailure
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.persistence.InMemoryStore
import core.infrastructure.type.isLeft
import core.infrastructure.type.left
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import organization.command.handler.AddOrganizationHandler
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
@RunWith(JUnitPlatform::class)
object AddOrganizationSpec: Spek({

    given("a store and a command bus") {
        val store = InMemoryStore<UUID, Organization>()
        val handlers = listOf(AddOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val name = "Name"
        val name2 = "Name 2"
        val secret = true
        val secret2 = false

        on("dispatch two AddOrganization commands") {
            commandBus.dispatch(AddOrganization(name, secret))
            commandBus.dispatch(AddOrganization(name2, secret2))

            it("should have the new organizations") {
                store.getAll().map { it.name } shouldContain name
                store.getAll().map { it.name } shouldContain name2
                store.getAll().size shouldEqual 2
            }
        }
    }

    given("a store and a command bus without handler") {
        val store = InMemoryStore<UUID, Organization>()
        val commandBus = CommandBus(emptyList())

        on("dispatch an AddOrganization command") {
            val result = commandBus.dispatch(AddOrganization("Name", true))

            it("should not have the new organization") {
                result.isLeft() shouldBe true
                result.left()!!::class shouldBe HandlerNotFoundFailure::class
                store.getAll().size shouldEqual 0
            }
        }
    }

})
