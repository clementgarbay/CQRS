package core.infrastructure.type

import java.time.ZonedDateTime

/**
 * @author Clément Garbay
 */
fun ZonedDateTime.isEqualOrBeforeNow(): Boolean = this.isEqualOrBefore(java.time.ZonedDateTime.now())
fun ZonedDateTime.isEqualOrBefore(date: ZonedDateTime): Boolean = this.isEqual(date) || this.isBefore(date)