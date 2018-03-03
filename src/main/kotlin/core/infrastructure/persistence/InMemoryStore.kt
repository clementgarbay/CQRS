package core.infrastructure.persistence

/**
 * @author Clément Garbay
 */
class InMemoryStore<K, T : IdBasedObject<K>> : Repository<K, T> {
    private val datastore = mutableMapOf<K, T>()

    override fun get(id: K): T? = datastore[id]

    override fun getAll(): List<T> = datastore.entries.map { it.value }.toList()

    override fun add(root: T) = datastore.put(root.id, root).toUnit()

    override fun delete(id: K) = datastore.remove(id).toUnit()

    companion object {
        fun Any?.toUnit() = Unit
    }
}

