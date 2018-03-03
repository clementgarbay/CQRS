package organization.command

import core.command.Command
import java.util.*

/**
 * @author Clément Garbay
 */
data class DeleteOrganization(val organizationId: UUID) : Command<Unit>