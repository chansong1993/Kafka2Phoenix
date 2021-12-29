package com.cmnit.gatherdata.modules.utils

import java.text.{ParseException, SimpleDateFormat}
import java.time.format.DateTimeFormatter
import java.time.temporal.{Temporal, TemporalAccessor, TemporalAmount, TemporalQuery}
import java.time._
import java.util.{Calendar, Date, GregorianCalendar}

import com.cmnit.gatherdata.utils.{ConcurrentDateFormat, Exceptions}

object DateUtil {
  val PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss"
  val PATTERN_DATE = "yyyy-MM-dd"
  val PATTERN_TIME = "HH:mm:ss"
  /**
   * 老 date 格式化
   */
  val DATETIME_FORMAT: ConcurrentDateFormat = ConcurrentDateFormat.of(PATTERN_DATETIME)
  val DATE_FORMAT: ConcurrentDateFormat = ConcurrentDateFormat.of(PATTERN_DATE)
  val TIME_FORMAT: ConcurrentDateFormat = ConcurrentDateFormat.of(PATTERN_TIME)
  /**
   * java 8 时间格式化
   */
  val DATETIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME)
  val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATE)
  val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DateUtil.PATTERN_TIME)

  /**
   * 获取当前日期
   *
   * @return 当前日期
   */
  def now = new Date

  /**
   * 添加年
   *
   * @param date       时间
   * @param yearsToAdd 添加的年数
   * @return 设置后的时间
   */
  def plusYears(date: Date, yearsToAdd: Int): Date = DateUtil.set(date, Calendar.YEAR, yearsToAdd)

  /**
   * 添加月
   *
   * @param date        时间
   * @param monthsToAdd 添加的月数
   * @return 设置后的时间
   */
  def plusMonths(date: Date, monthsToAdd: Int): Date = DateUtil.set(date, Calendar.MONTH, monthsToAdd)

  /**
   * 添加周
   *
   * @param date       时间
   * @param weeksToAdd 添加的周数
   * @return 设置后的时间
   */
  def plusWeeks(date: Date, weeksToAdd: Int): Date = DateUtil.plus(date, Period.ofWeeks(weeksToAdd))

  /**
   * 添加天
   *
   * @param date      时间
   * @param daysToAdd 添加的天数
   * @return 设置后的时间
   */
  def plusDays(date: Date, daysToAdd: Long): Date = DateUtil.plus(date, Duration.ofDays(daysToAdd))

  /**
   * 添加天
   *
   * @param dateStr   时间
   * @param daysToAdd 添加的天数
   * @return 设置后的时间
   */
  def plusDays(dateStr: String, daysToAdd: Long): String = {
    val simple = new SimpleDateFormat(dateStr)
    try {
      val date = DateUtil.plusDays(simple.parse(dateStr), daysToAdd)
      simple.format(date)
    } catch {
      case e: ParseException =>
        e.printStackTrace()
        throw new RuntimeException("格式化时间错误")
    }
  }

  /**
   * 添加小时
   *
   * @param date       时间
   * @param hoursToAdd 添加的小时数
   * @return 设置后的时间
   */
  def plusHours(date: Date, hoursToAdd: Long): Date = DateUtil.plus(date, Duration.ofHours(hoursToAdd))

  /**
   * 添加分钟
   *
   * @param date         时间
   * @param minutesToAdd 添加的分钟数
   * @return 设置后的时间
   */
  def plusMinutes(date: Date, minutesToAdd: Long): Date = DateUtil.plus(date, Duration.ofMinutes(minutesToAdd))

  /**
   * 添加秒
   *
   * @param date         时间
   * @param secondsToAdd 添加的秒数
   * @return 设置后的时间
   */
  def plusSeconds(date: Date, secondsToAdd: Long): Date = DateUtil.plus(date, Duration.ofSeconds(secondsToAdd))

  /**
   * 添加毫秒
   *
   * @param date        时间
   * @param millisToAdd 添加的毫秒数
   * @return 设置后的时间
   */
  def plusMillis(date: Date, millisToAdd: Long): Date = DateUtil.plus(date, Duration.ofMillis(millisToAdd))

  /**
   * 添加纳秒
   *
   * @param date       时间
   * @param nanosToAdd 添加的纳秒数
   * @return 设置后的时间
   */
  def plusNanos(date: Date, nanosToAdd: Long): Date = DateUtil.plus(date, Duration.ofNanos(nanosToAdd))

  /**
   * 日期添加时间量
   *
   * @param date   时间
   * @param amount 时间量
   * @return 设置后的时间
   */
  def plus(date: Date, amount: TemporalAmount): Date = {
    val instant = date.toInstant
    Date.from(instant.plus(amount))
  }

  /**
   * 减少年
   *
   * @param date  时间
   * @param years 减少的年数
   * @return 设置后的时间
   */
  def minusYears(date: Date, years: Int): Date = DateUtil.set(date, Calendar.YEAR, -years)

  /**
   * 减少月
   *
   * @param date   时间
   * @param months 减少的月数
   * @return 设置后的时间
   */
  def minusMonths(date: Date, months: Int): Date = DateUtil.set(date, Calendar.MONTH, -months)

  /**
   * 减少周
   *
   * @param date  时间
   * @param weeks 减少的周数
   * @return 设置后的时间
   */
  def minusWeeks(date: Date, weeks: Int): Date = DateUtil.minus(date, Period.ofWeeks(weeks))

  /**
   * 减少天
   *
   * @param date 时间
   * @param days 减少的天数
   * @return 设置后的时间
   */
  def minusDays(date: Date, days: Long): Date = DateUtil.minus(date, Duration.ofDays(days))

  /**
   * 减少小时
   *
   * @param date  时间
   * @param hours 减少的小时数
   * @return 设置后的时间
   */
  def minusHours(date: Date, hours: Long): Date = DateUtil.minus(date, Duration.ofHours(hours))

  /**
   * 减少分钟
   *
   * @param date    时间
   * @param minutes 减少的分钟数
   * @return 设置后的时间
   */
  def minusMinutes(date: Date, minutes: Long): Date = DateUtil.minus(date, Duration.ofMinutes(minutes))

  /**
   * 减少秒
   *
   * @param date    时间
   * @param seconds 减少的秒数
   * @return 设置后的时间
   */
  def minusSeconds(date: Date, seconds: Long): Date = DateUtil.minus(date, Duration.ofSeconds(seconds))

  /**
   * 减少毫秒
   *
   * @param date   时间
   * @param millis 减少的毫秒数
   * @return 设置后的时间
   */
  def minusMillis(date: Date, millis: Long): Date = DateUtil.minus(date, Duration.ofMillis(millis))

  /**
   * 减少纳秒
   *
   * @param date  时间
   * @param nanos 减少的纳秒数
   * @return 设置后的时间
   */
  def minusNanos(date: Date, nanos: Long): Date = DateUtil.minus(date, Duration.ofNanos(nanos))

  /**
   * 日期减少时间量
   *
   * @param date   时间
   * @param amount 时间量
   * @return 设置后的时间
   */
  def minus(date: Date, amount: TemporalAmount): Date = {
    val instant = date.toInstant
    Date.from(instant.minus(amount))
  }

  /**
   * 设置日期属性
   *
   * @param date          时间
   * @param calendarField 更改的属性
   * @param amount        更改数，-1表示减少
   * @return 设置后的时间
   */
  private def set(date: Date, calendarField: Int, amount: Int) = {
    if (date == null) {
      System.out.println("The date must not be null")
      throw new RuntimeException("The date must not be null")
    }
    val c = Calendar.getInstance
    c.setLenient(false)
    c.setTime(date)
    c.add(calendarField, amount)
    c.getTime
  }

  /**
   * 日期时间格式化
   *
   * @param date 时间
   * @return 格式化后的时间
   */
  def formatDateTime(date: Date): String = DATETIME_FORMAT.format(date)

  /**
   * 日期格式化
   *
   * @param date 时间
   * @return 格式化后的时间
   */
  def formatDate(date: Date): String = DATE_FORMAT.format(date)

  /**
   * 时间格式化
   *
   * @param date 时间
   * @return 格式化后的时间
   */
  def formatTime(date: Date): String = TIME_FORMAT.format(date)

  /**
   * 日期格式化
   *
   * @param date    时间
   * @param pattern 表达式
   * @return 格式化后的时间
   */
  def format(date: Date, pattern: String): String = ConcurrentDateFormat.of(pattern).format(date)

  /**
   * java8 日期时间格式化
   *
   * @param temporal 时间
   * @return 格式化后的时间
   */
  def formatDateTime(temporal: TemporalAccessor): String = DATETIME_FORMATTER.format(temporal)

  def formatDate(temporal: TemporalAccessor): String = DATE_FORMATTER.format(temporal)

  /**
   * java8 时间格式化
   *
   * @param temporal 时间
   * @return 格式化后的时间
   */
  def formatTime(temporal: TemporalAccessor): String = TIME_FORMATTER.format(temporal)

  /**
   * java8 日期格式化
   *
   * @param temporal 时间
   * @param pattern  表达式
   * @return 格式化后的时间
   */
  def format(temporal: TemporalAccessor, pattern: String): String = DateTimeFormatter.ofPattern(pattern).format(temporal)

  /**
   * 将字符串转换为时间
   *
   * @param dateStr 时间字符串
   * @param pattern 表达式
   * @return 时间
   */
  def parse(dateStr: String, pattern: String): Date = {
    val format = ConcurrentDateFormat.of(pattern)
    try format.parse(dateStr)
    catch {
      case e: ParseException =>
        throw Exceptions.unchecked(e)
    }
  }

  /**
   * 将字符串转换为时间
   *
   * @param dateStr 时间字符串
   * @param format  ConcurrentDateFormat
   * @return 时间
   */
  def parse(dateStr: String, format: ConcurrentDateFormat): Date = try format.parse(dateStr)
  catch {
    case e: ParseException =>
      throw Exceptions.unchecked(e)
  }

  def parse[T](dateStr: String, pattern: String, query: TemporalQuery[T]): T = DateTimeFormatter.ofPattern(pattern).parse(dateStr, query)

  /**
   * 时间转 Instant
   *
   * @param dateTime 时间
   * @return Instant
   */
  def toInstant(dateTime: LocalDateTime): Instant = dateTime.atZone(ZoneId.systemDefault).toInstant

  /**
   * Instant 转 时间
   *
   * @param instant Instant
   * @return Instant
   */
  def toDateTime(instant: Instant): LocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault)

  /**
   * 转换成 date
   *
   * @param dateTime LocalDateTime
   * @return Date
   */
  def toDate(dateTime: LocalDateTime): Date = Date.from(DateUtil.toInstant(dateTime))

  /**
   * 转换成 date
   *
   * @param localDate LocalDate
   * @return Date
   */
  def toDate(localDate: LocalDate): Date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault).toInstant)

  /**
   * Converts local date time to Calendar.
   */
  def toCalendar(localDateTime: LocalDateTime): Calendar = GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault))

  /**
   * localDateTime 转换成毫秒数
   *
   * @param localDateTime LocalDateTime
   * @return long
   */
  def toMilliseconds(localDateTime: LocalDateTime): Long = localDateTime.atZone(ZoneId.systemDefault).toInstant.toEpochMilli

  /**
   * localDate 转换成毫秒数
   *
   * @param localDate LocalDate
   * @return long
   */
  def toMilliseconds(localDate: LocalDate): Long = toMilliseconds(localDate.atStartOfDay)

  /**
   * 转换成java8 时间
   *
   * @param calendar 日历
   * @return LocalDateTime
   */
  def fromCalendar(calendar: Calendar): LocalDateTime = {
    val tz = calendar.getTimeZone
    val zid = if (tz == null) ZoneId.systemDefault
    else tz.toZoneId
    LocalDateTime.ofInstant(calendar.toInstant, zid)
  }

  /**
   * 转换成java8 时间
   *
   * @param instant Instant
   * @return LocalDateTime
   */
  def fromInstant(instant: Instant): LocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault)

  /**
   * 转换成java8 时间
   *
   * @param date Date
   * @return LocalDateTime
   */
  def fromDate(date: Date): LocalDateTime = LocalDateTime.ofInstant(date.toInstant, ZoneId.systemDefault)

  /**
   * 转换成java8 时间
   *
   * @param milliseconds 毫秒数
   * @return LocalDateTime
   */
  def fromMilliseconds(milliseconds: Long): LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault)

  /**
   * 比较2个时间差，跨度比较小
   *
   * @param startInclusive 开始时间
   * @param endExclusive   结束时间
   * @return 时间间隔
   */
  def between(startInclusive: Temporal, endExclusive: Temporal): Duration = Duration.between(startInclusive, endExclusive)

  /**
   * 比较2个时间差，跨度比较大，年月日为单位
   *
   * @param startDate 开始时间
   * @param endDate   结束时间
   * @return 时间间隔
   */
  def between(startDate: LocalDate, endDate: LocalDate): Period = Period.between(startDate, endDate)

  /**
   * 比较2个 时间差
   *
   * @param startDate 开始时间
   * @param endDate   结束时间
   * @return 时间间隔
   */
  def between(startDate: Date, endDate: Date): Duration = Duration.between(startDate.toInstant, endDate.toInstant)

  /**
   * 获取今天的日期
   *
   * @return 时间
   */
  def today: String = format(new Date, "yyyyMMdd")


  /**
   * 获取每隔5分的取整时间
   *
   * @return
   */
  def getFullTime5(dateStr: String): String = {
    val minute = dateStr.substring(dateStr.length - 4, dateStr.length - 3)
    val builder = new StringBuffer(dateStr)
    builder.replace(dateStr.length - 2, dateStr.length, "00")
    if (Integer.valueOf(minute).compareTo(5) >= 0) {
      builder.replace(dateStr.length - 4, dateStr.length - 3, "5")
    } else {
      builder.replace(dateStr.length - 4, dateStr.length - 3, "0")
    }
    builder.toString
  }


  /**
   * 获取每隔15分的取整时间
   *
   * @return
   */
  def getFullTime15(dateStr: String): String = {
    val minute = dateStr.substring(dateStr.length - 5, dateStr.length - 3)
    val builder = new StringBuffer(dateStr)
    builder.replace(dateStr.length - 2, dateStr.length, "00")
    if (Integer.valueOf(minute) >= 1 && Integer.valueOf(minute) <= 15) {
      builder.replace(dateStr.length - 5, dateStr.length - 3, "00")
    } else if (Integer.valueOf(minute) > 15 && Integer.valueOf(minute) <= 30) {
      builder.replace(dateStr.length - 5, dateStr.length - 3, "15")
    } else if (Integer.valueOf(minute) > 30 && Integer.valueOf(minute) <= 45) {
      builder.replace(dateStr.length - 5, dateStr.length - 3, "30")
    } else if (Integer.valueOf(minute) == 0 || Integer.valueOf(minute) > 45) {
      builder.replace(dateStr.length - 5, dateStr.length - 3, "45")
    }
    builder.toString
  }
}
