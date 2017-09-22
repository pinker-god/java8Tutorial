package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xd031 on 2017/9/22.
 * 会报错可以明显地看出!
 */
public class DateFormatDemo03 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Callable<LocalDate> task = () -> LocalDate.parse("2017:03:13", DateTimeFormatter.ofPattern("yyyy:MM:dd"));
    ExecutorService pool = Executors.newFixedThreadPool(10);
    List<Future<LocalDate>> futureList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      futureList.add(pool.submit(task));
    }
    for (Future<LocalDate> future : futureList) {
      System.out.println(future.get());
    }
    pool.shutdown();
  }
}
