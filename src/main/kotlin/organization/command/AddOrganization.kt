package organization.command

import core.command.Command

data class AddOrganization(val name: String, val secret: Boolean) : Command<Unit>