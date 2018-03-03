package organization.command

import core.command.Command

data class AddOrganization(val name: String, val public: Boolean) : Command<Unit>