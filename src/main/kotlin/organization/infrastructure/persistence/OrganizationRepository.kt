package organization.infrastructure.persistence

import core.infrastructure.persistence.Repository
import organization.domain.Organization
import java.util.*

/**
 * @author Clément Garbay
 */
class OrganizationRepository : Repository<UUID, Organization> {
    // Here implement the business logic to store and retrieve organization

    override fun get(id: UUID): Organization {
        print("[organization.infrastructure.persistence.OrganizationRepository] Get: $id")

        TODO("not implemented")
    }

    override fun getAll(): List<Organization> {
        print("[organization.infrastructure.persistence.OrganizationRepository] Get all")

        TODO("not implemented")
    }

    override fun add(root: Organization) {
        print("[organization.infrastructure.persistence.OrganizationRepository] Add: $root")

        TODO("not implemented")
    }

    override fun delete(id: UUID) {
        print("[organization.infrastructure.persistence.OrganizationRepository] Delete: $id")

        TODO("not implemented")
    }
}
