package organization.configuration.injector

import com.github.salomonbrys.kodein.*
import core.command.CommandHandler
import core.infrastructure.persistence.InMemoryStore
import core.infrastructure.persistence.Repository
import core.infrastructure.resource.Resource
import organization.command.handler.AddOrganizationHandler
import organization.command.handler.DeleteOrganizationHandler
import organization.command.handler.RenameOrganizationHandler
import organization.domain.Organization
import organization.infrastructure.resource.OrganizationResource
import java.util.*

/**
 * @author Clément Garbay
 */
object OrganizationModule {

    val bindings = Kodein.Module {
        // Bind organization handlers to application handlers set
        bind<CommandHandler<*, *>>().inSet() with singleton { AddOrganizationHandler(instance()) }
        bind<CommandHandler<*, *>>().inSet() with singleton { RenameOrganizationHandler(instance()) }
        bind<CommandHandler<*, *>>().inSet() with singleton { DeleteOrganizationHandler(instance()) }

        // Bind organization resource to application resources set
        bind<Resource>().inSet() with singleton { OrganizationResource(instance()) }

        // Bind organization repository to in memory data store
        bind<Repository<UUID, Organization>>() with singleton { InMemoryStore<UUID, Organization>() }
    }

}
