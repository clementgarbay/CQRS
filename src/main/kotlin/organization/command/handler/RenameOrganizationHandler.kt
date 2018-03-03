package organization.command.handler

import core.command.CommandHandler
import core.infrastructure.persistence.Repository
import organization.command.RenameOrganization
import organization.domain.Organization
import java.util.*
import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
class RenameOrganizationHandler(
    private val organizationRepository: Repository<UUID, Organization>
) : CommandHandler<RenameOrganization, Unit> {
    override fun listenTo(): KClass<RenameOrganization> = RenameOrganization::class

    override fun handle(message: RenameOrganization) {
        val organization = organizationRepository.get(message.organizationId)

        if (organization != null) {
            organizationRepository.add(organization.rename(message.newOrganizationName))
        }
    }
}
