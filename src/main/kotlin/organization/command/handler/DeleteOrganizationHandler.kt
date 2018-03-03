package organization.command.handler

import core.command.CommandHandler
import core.infrastructure.persistence.Repository
import organization.command.DeleteOrganization
import organization.domain.Organization
import java.util.*
import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
class DeleteOrganizationHandler(
    private val organizationRepository: Repository<UUID, Organization>
) : CommandHandler<DeleteOrganization, Unit> {
    override fun listenTo(): KClass<DeleteOrganization> = DeleteOrganization::class

    override fun handle(message: DeleteOrganization) {
        organizationRepository.delete(message.organizationId)
    }
}
