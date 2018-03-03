package organization

import core.command.CommandBus
import core.infrastructure.persistence.InMemoryStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import organization.command.DeleteOrganization
import organization.command.handler.DeleteOrganizationHandler
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
class DeleteOrganizationTest {

    private val store = InMemoryStore<UUID, Organization>()

    @Test
    fun deleteOrganizationEmpty() {
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))

        val handlers = listOf(DeleteOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        commandBus.dispatch(DeleteOrganization(id))

        assertThat(store.get(id) == null).isTrue()
        assertThat(store.getAll()).hasSize(0)
    }

    @Test
    fun deleteOrganizationRest() {
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))
        store.add(Organization(UUID.randomUUID(), "Name 1", true))
        store.add(Organization(UUID.randomUUID(), "Name 2", false))

        val handlers = listOf(DeleteOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        commandBus.dispatch(DeleteOrganization(id))

        assertThat(store.get(id) == null).isTrue()
        assertThat(store.getAll()).hasSize(2)
    }

    @Test
    fun deleteOrganizationWithoutHandler() {
        val id = UUID.randomUUID()
        store.add(Organization(id, "Name", false))

        val commandBus = CommandBus(emptyList())

        commandBus.dispatch(DeleteOrganization(id))

        assertThat(store.get(id) != null).isTrue()
        assertThat(store.getAll()).hasSize(1)
    }
}