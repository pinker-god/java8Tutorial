package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xd031 on 2017/9/22.
 * 会报错可以明显地看出!
 */
public class SimpleDateFormatDemo01 {
  public static void main(String[] args) throws ExecutionException, InterruptedException, ParseException {


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Callable<Date> task = () -> format.parse("2017-09-13");

    ExecutorService pool = Executors.newFixedThreadPool(10);
    List<Future<Date>> dateList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Future<Date> future = pool.submit(task);
      dateList.add(future);
    }

    for (Future future : dateList) {
      System.out.println(future.get());
    }
    pool.shutdown();
  }
}
