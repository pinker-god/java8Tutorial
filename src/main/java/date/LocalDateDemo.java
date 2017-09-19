package date;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.*;
import java.util.Date;

/**
 * Created by xd031 on 2017/8/6.
 * 异步化!
 */
public class LocalDateDemo {
  public static void main(String[] args) {
    // testJava8Date();
    //compareSqlTimeAndDateTime();
//    testLocalDate();
//    testLocalTime();
//    testInstant();
//    testPeriod();
//    localDateMethodTest();
//    formatTest();
//    testMonthDay();
    testYearMonth();
  }

  private static void testYearMonth() {
    LocalDate localDate = LocalDate.now();
    localDate = localDate.plus(2, ChronoUnit.DAYS);
    LocalDate firstDays = localDate.with(TemporalAdjusters.firstDayOfMonth());
    LocalDate lastDays = localDate.with(TemporalAdjusters.lastDayOfMonth());
    int days = YearMonth.from(localDate).lengthOfMonth();
    System.out.println(localDate + "->" + firstDays + "->" + lastDays + "->" + days);//需求完成
    LocalDate date = YearMonth.from(localDate).atDay(13);
    System.out.println(date);
  }

  /**
   * 节日或周期性时间
   */
  private static void testMonthDay() {
    MonthDay birthday = MonthDay.of(12, 26);
    MonthDay currentDay = MonthDay.from(LocalDate.of(2017, 12, 26));
    MonthDay currentDay1 = MonthDay.from(LocalDate.now());
    System.out.println(birthday);
    System.out.println(currentDay);
    System.out.println(birthday.equals(currentDay));//true
    System.out.println(birthday.equals(currentDay1));//false
  }

  private static void formatTest() {
    LocalDate date = LocalDate.now();
    System.out.println(date);
    System.out.println(date.format(DateTimeFormatter.ofPattern("d::yyyy::MM")));
    System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
    System.out.println(date.format(DateTimeFormatter.ISO_DATE));
    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));//给前端传的数据格式
    System.out.println(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));//转换为字符串很方便的截取
  }

  private static void localDateMethodTest() {
    LocalDate date = LocalDate.now();
    LocalDateTime dateTime = date.atTime(LocalTime.now());
    System.out.println(date + "-->" + dateTime);
    int days = date.getDayOfMonth();
    System.out.println(days);
    boolean flag = date.isLeapYear();
    boolean after = date.isAfter(LocalDate.of(2019, 12, 26));
    boolean before = date.isBefore(LocalDate.of(2017, 9, 6));
    System.out.println(flag + "\t" + after + "\t" + before);

    //plus,minus
    System.out.println(date.plusDays(2));
    System.out.println(date.plusWeeks(1));
    System.out.println(date.minusYears(2));

    //with,TemporalAdjuster可以调整日期
    System.out.println("------------------with---------------------------");
    System.out.println(date.with(TemporalAdjusters.firstDayOfMonth()));
    System.out.println(date.with(TemporalAdjusters.lastDayOfYear()));
    System.out.println(date.withDayOfYear(300));//这个API应该很有用!
    System.out.println(date.withDayOfMonth(2));
    System.out.println(date.withMonth(2));
    System.out.println(date.withYear(2019));
    Period period = date.until(date.withDayOfYear(200));
    System.out.println(period);
    System.out.println(date.withDayOfYear(200));
  }

  /**
   * endTime-startTime
   */
  private static void testPeriod() {
    Period period = Period.between(LocalDate.of(2019, 12, 26), LocalDate.now());
    System.out.println(period.getYears());
    System.out.println(period.getMonths());
    System.out.println(period.getDays());
  }

  private static void testInstant() {
    Instant time = Instant.now();
    System.out.println(time);
  }

  private static void testLocalTime() {
    LocalTime time = LocalTime.of(12, 20, 25, 50);
    System.out.println(time);

    LocalTime time1 = LocalTime.ofSecondOfDay(10000);
    System.out.println(time1);
  }

  private static void testLocalDate() {
    LocalDate date = LocalDate.of(2014, 1, 1);
    System.out.println(date);
    //Getting date from the base date i.e 01/01/1970
    LocalDate dateFromBase = LocalDate.ofEpochDay(365);
    System.out.println("365th day from base date= " + dateFromBase);

    LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
    System.out.println("100th day of 2014=" + hundredDay2014);
  }

  private static void compareSqlTimeAndDateTime() {
    Date date = new Date();
    System.out.println(date);
    java.sql.Date date1 = new java.sql.Date(new Date().getTime());
    System.out.println(date1);
  }

  //三个基本类LocalDate,LocalTime,LocalDateTime
  private static void testJava8Date() {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    LocalDate yesterday = today.minusDays(1);
    System.out.println(today);
    System.out.println(tomorrow);
    System.out.println(yesterday);

    System.out.println(today.getMonth());
    System.out.println(today.getDayOfMonth());
    System.out.println(today.getDayOfWeek());
    System.out.println(today.getDayOfYear());
    System.out.println(today.getMonthValue());
    System.out.println(today.getYear());

    LocalTime time = LocalTime.now();
    System.out.println(time.getHour());
    System.out.println(time.getMinute());
    System.out.println(time.getSecond());

    LocalDateTime current = LocalDateTime.now();
    System.out.println(current.getHour());
    System.out.println(current.getMinute());
    System.out.println(current.getSecond());
  }

}
