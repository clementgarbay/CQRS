package core.infrastructure.type

/**
 * https://github.com/adelnizamutdinov/kotlin-either
 */
sealed class Either<out L, out R>
data class Left<out T>(val value: T) : Either<T, Nothing>()
data class Right<out T>(val value: T) : Either<Nothing, T>()

inline fun <L, R, T> Either<L, R>.fold(left: (L) -> T, right: (R) -> T): T =
    when (this) {
        is Left  -> left(value)
        is Right -> right(value)
    }

inline fun <L, R, T> Either<L, R>.flatMap(f: (R) -> Either<L, T>): Either<L, T> =
    fold({ this as Left }, f)

inline fun <L, R, T> Either<L, R>.map(f: (R) -> T): Either<L, T> =
    flatMap { Right(f(it)) }

fun <L, R> Either<L, R>.isLeft(): Boolean = this is Left<L>
fun <L, R> Either<L, R>.isRight(): Boolean = this is Right<R>

fun <L, R> Either<L, R>.left(): L? =
    when (this) {
        is Left  -> value
        else -> null
    }
fun <L, R> Either<L, R>.right(): R? =
    when (this) {
        is Right -> value
        else -> null
    }
