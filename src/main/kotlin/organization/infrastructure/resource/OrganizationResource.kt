package organization.infrastructure.resource

import core.infrastructure.bus.command.CommandBus
import organization.command.RenameOrganization
import organization.command.handler.RenameOrganizationHandler
import organization.infrastructure.persistence.OrganizationRepository
import java.util.*

/**
 * @author Clément Garbay
 */
class OrganizationResource(private val commandBus: CommandBus) {

    // @Put
    // @Path('/organization/rename')
    fun rename() {
        val organizationId = UUID.randomUUID()
        val newOrganizationName = "New name"

        RenameOrganizationHandler(OrganizationRepository())
                .handle(RenameOrganization(organizationId, newOrganizationName))


        commandBus.dispatch(RenameOrganization(organizationId, newOrganizationName))
    }
}


