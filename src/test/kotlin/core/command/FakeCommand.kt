package core.command

import javax.validation.constraints.NotEmpty

/**
 * @author Clément Garbay
 */
data class FakeCommand(@get:NotEmpty val name: String) : Command<Unit>
