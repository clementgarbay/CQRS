package organization.command

import core.failure.HandlerNotFoundFailure
import core.infrastructure.bus.command.CommandBus
import core.infrastructure.persistence.InMemoryStore
import core.infrastructure.type.isLeft
import core.infrastructure.type.left
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import organization.command.handler.RenameOrganizationHandler
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
@RunWith(JUnitPlatform::class)
object RenameOrganizationSpec: Spek({

    given("a store containing an organization and a command bus") {
        val store = InMemoryStore<UUID, Organization>()
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))

        val handlers = listOf(RenameOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val newName = "New Name"

        on("dispatch a RenameOrganization command") {
            commandBus.dispatch(RenameOrganization(id, newName))

            it("should have the organization with the new name") {
                store.get(id)!!.name shouldEqual newName
                store.getAll().size shouldEqual 1
            }
        }
    }

    given("a store containing three organizations and a command bus") {
        val store = InMemoryStore<UUID, Organization>()
        val id = UUID.randomUUID()
        val id2 = UUID.randomUUID()
        val name1 = "Name 1"
        store.add(Organization(id, "Name", false))
        store.add(Organization(id2, name1, true))
        store.add(Organization(UUID.randomUUID(), "Name 2", false))

        val handlers = listOf(RenameOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val newName = "New Name"

        on("dispatch a RenameOrganization command") {
            commandBus.dispatch(RenameOrganization(id, newName))

            it("should not rename others organizations") {
                store.get(id)!!.name shouldEqual newName
                store.get(id2)!!.name shouldEqual name1
                store.getAll().size shouldEqual 3
            }
        }
    }

    given("a store and a command bus without handler") {
        val store = InMemoryStore<UUID, Organization>()
        val id = UUID.randomUUID()
        val name = "Name"
        store.add(Organization(id, name, false))

        val commandBus = CommandBus(emptyList())

        on("dispatch a RenameOrganization command") {
            val result = commandBus.dispatch(RenameOrganization(id, "New Name"))

            it("should not rename others organizations") {
                result.isLeft() shouldBe true
                result.left()!!::class shouldBe HandlerNotFoundFailure::class
                store.get(id)!!.name shouldEqual name
                store.getAll().size shouldEqual 1
            }
        }
    }

})
