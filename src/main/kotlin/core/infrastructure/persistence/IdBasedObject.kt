package core.infrastructure.persistence

/**
 * @author Clément Garbay
 */
abstract class IdBasedObject<out T>(open val id: T)