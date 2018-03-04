package organization.command

import core.infrastructure.bus.command.CommandBus
import core.infrastructure.persistence.InMemoryStore
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import organization.command.handler.DeleteOrganizationHandler
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
@RunWith(JUnitPlatform::class)
object DeleteOrganizationSpec: Spek({

    given("a store containing an organization and a command bus") {
        val store = InMemoryStore<UUID, Organization>()
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))

        val handlers = listOf(DeleteOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        on("dispatch a DeleteOrganization command") {
            commandBus.dispatch(DeleteOrganization(id))

            it("should not have the deleted organization") {
                (store.get(id) == null) shouldBe true
                store.getAll().size shouldEqual 0
            }
        }
    }

    given("a store containing three organizations and a command bus") {
        val store = InMemoryStore<UUID, Organization>()
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))
        store.add(Organization(UUID.randomUUID(), "Name 1", true))
        store.add(Organization(UUID.randomUUID(), "Name 2", false))

        val handlers = listOf(DeleteOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        on("dispatch a DeleteOrganization command") {
            commandBus.dispatch(DeleteOrganization(id))

            it("should not have the deleted organization") {
                (store.get(id) == null) shouldBe true
                store.getAll().size shouldEqual 2
            }
        }
    }

    given("a store containing three organizations and a command bus without handler") {
        val store = InMemoryStore<UUID, Organization>()
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))

        val commandBus = CommandBus(emptyList())

        on("dispatch a DeleteOrganization command") {
            commandBus.dispatch(DeleteOrganization(id))

            it("should have the deleted organization") {
                (store.get(id) != null) shouldBe true
                store.getAll().size shouldEqual 1
            }
        }
    }

})