package organization.command.handler

import core.command.CommandHandler
import core.infrastructure.persistence.Repository
import organization.command.AddOrganization
import organization.domain.Organization
import java.util.*
import kotlin.reflect.KClass

/**
 * @author Clément Garbay
 */
class AddOrganizationHandler(
    private val organizationRepository: Repository<UUID, Organization>
) : CommandHandler<AddOrganization, Unit> {
    override fun listenTo(): KClass<AddOrganization> = AddOrganization::class

    override fun handle(message: AddOrganization) {
        organizationRepository.add(Organization(UUID.randomUUID(), message.name, message.secret))
    }
}
