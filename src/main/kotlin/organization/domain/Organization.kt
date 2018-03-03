package organization.domain

import core.infrastructure.persistence.IdBasedObject
import java.util.*

/**
 * @author Clément Garbay
 */
data class Organization(
    override val id: UUID,
    val name: String,
    val secret: Boolean
) : IdBasedObject<UUID>(id) {
    fun rename(newName: String) = this.copy(name = newName)
}