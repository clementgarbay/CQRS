package organization

import core.infrastructure.bus.command.CommandBus
import core.infrastructure.persistence.InMemoryStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import organization.command.AddOrganization
import organization.command.handler.AddOrganizationHandler
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
class AddOrganizationTest {

    private val store = InMemoryStore<UUID, Organization>()

    @Test
    fun addOrganizationEmpty() {
        val handlers = listOf(AddOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val name = "Name"
        val secret = true

        commandBus.dispatch(AddOrganization(name, secret))

        assertThat(store.getAll().first()).hasFieldOrPropertyWithValue("name", name)
        assertThat(store.getAll().first()).hasFieldOrPropertyWithValue("secret", secret)
        assertThat(store.getAll()).hasSize(1)
    }

    @Test
    fun addOrganizationRest() {
        val handlers = listOf(AddOrganizationHandler(store))
        val commandBus = CommandBus(handlers)

        val name = "Name"
        val name2 = "Name 2"
        val secret = true
        val secret2 = false

        commandBus.dispatch(AddOrganization(name, secret))
        commandBus.dispatch(AddOrganization(name2, secret2))

        assertThat(store.getAll().map { it.name }).containsExactly(name, name2)
        assertThat(store.getAll().map { it.secret }).containsExactly(secret, secret2)
        assertThat(store.getAll()).hasSize(2)
    }

    @Test
    fun addOrganizationWithoutHandler() {
        val commandBus = CommandBus(emptyList())

        commandBus.dispatch(AddOrganization("Name", true))

        assertThat(store.getAll()).hasSize(0)
    }
}