package core.exception

/**
 * @author Clément Garbay
 */
class ValidationException(val messages: List<String>) : RuntimeException()