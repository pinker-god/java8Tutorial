package date;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xd031 on 2017/9/22.
 */
public class SimpleDateFormatDemo02 {
  private static final ThreadLocal<DateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

  public static Date convert(String source) throws ParseException {
    return dateFormat.get().parse(source);
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Callable<Date> task = () -> SimpleDateFormatDemo02.convert("2017-08-23");
    ExecutorService pool = Executors.newFixedThreadPool(10);
    List<Future<Date>> futureList = new ArrayList<>();
    for (int i = 0; i < 10; i++)
      futureList.add(pool.submit(task));
    for (Future<Date> future : futureList)
      System.out.println(future.get());
    pool.shutdown();
  }
}
