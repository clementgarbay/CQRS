package organization

import core.infrastructure.bus.command.CommandBus
import core.infrastructure.persistence.InMemoryStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import organization.command.RenameOrganization
import organization.command.handler.RenameOrganizationHandler
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
class RenameOrganizationTest {

    private val store = InMemoryStore<UUID, Organization>()

    @Test
    fun renameOrganizationEmpty() {
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))

        val handlers = listOf(RenameOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val newName = "New Name"

        commandBus.dispatch(RenameOrganization(id, newName))

        assertThat(store.get(id)).hasFieldOrPropertyWithValue("name", newName)
        assertThat(store.getAll()).hasSize(1)
    }

    @Test
    fun renameOrganizationRest() {
        val id = UUID.randomUUID()
        val id2 = UUID.randomUUID()
        val name1 = "Name 1"
        store.add(Organization(id, "Name", false))
        store.add(Organization(id2, name1, true))
        store.add(Organization(UUID.randomUUID(), "Name 2", false))

        val handlers = listOf(RenameOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val newName = "New Name"

        commandBus.dispatch(RenameOrganization(id, newName))

        assertThat(store.get(id)).hasFieldOrPropertyWithValue("name", newName)
        assertThat(store.get(id2)).hasFieldOrPropertyWithValue("name", name1)
        assertThat(store.getAll()).hasSize(3)
    }

    @Test
    fun renameOrganizationWithoutHandler() {
        val id = UUID.randomUUID()
        val name = "Name"
        store.add(Organization(id, name, false))

        val commandBus = CommandBus(emptyList())

        commandBus.dispatch(RenameOrganization(id, "New Name"))

        assertThat(store.get(id)).hasFieldOrPropertyWithValue("name", name)
        assertThat(store.getAll()).hasSize(1)
    }
}