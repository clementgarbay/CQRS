package organization.command

import core.command.Command
import java.util.*

/**
 * @author Clément Garbay
 */
data class RenameOrganization(val organizationId: UUID, val newOrganizationName: String) : Command<Unit>