package date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by xd031 on 2017/8/6.
 */
public class LocalDateDemo {
  public static void main(String[] args) {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    LocalDate yesterday = today.minusDays(2);
    System.out.println(today);
    System.out.println(tomorrow);
    System.out.println(yesterday);
  }
}
