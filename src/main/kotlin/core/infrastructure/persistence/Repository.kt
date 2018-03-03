package core.infrastructure.persistence

/**
 * @author Clément Garbay
 */
interface Repository<TId, TRoot : IdBasedObject<TId>> {
    fun get(id: TId): TRoot?
    fun getAll(): List<TRoot>
    fun add(root: TRoot)
    fun delete(id: TId)
}