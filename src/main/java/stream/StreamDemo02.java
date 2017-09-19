package stream;

import java.util.*;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by xd031 on 2017/9/19.
 */
public class StreamDemo02 {
  public static void main(String[] args) {
//    countAvg();
//    summaryStatistics();
//    intListToMap();
//    intsFunction();
//    randomTest();
    arrayReferrence();
  }

  private static void arrayReferrence() {
    Function<Integer, String[]> fun = String[]::new;
    String[] strs = fun.apply(20);
    System.out.println(strs.length);
  }

  private static void randomTest() {
    Random r1 = new Random(100);
    Random r2 = new Random(100);
    List<Integer> randomList = getNumList(10, () -> r1.nextInt(100));
    List<Integer> randomList1 = getNumList(10, () -> r2.nextInt(100));
    List<Integer> randomList2 = getNumList(10, () -> (int) (Math.random() * 100));
    System.out.println(randomList);
    System.out.println(randomList1);
    System.out.println(randomList2);
  }

  private static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      list.add(supplier.get());
    }
    return list;
  }

  private static void intsFunction() {
    List<Integer> list = new Random().ints(-100, 100).limit(250).boxed().collect(Collectors.toList());
    Optional<Integer> max = new Random().ints(-100, 100).limit(200).boxed().reduce(Math::max);
    max.ifPresent(System.out::print);
  }

  private static void intListToMap() {
    IntStream.range(0, 100).boxed().collect(Collectors.toMap(key -> key, value -> value * 4)).forEach((key, value) -> System.out.print(key + ":" + value + "\t"));
    Map<Integer, Integer> map = IntStream.range(0, 100).boxed().collect(Collectors.toMap(key -> key, value -> value * 4));
  }

  private static void summaryStatistics() {
    List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
    IntSummaryStatistics iss = list.stream().collect(Collectors.summarizingInt(value -> value));
    System.out.println(iss);
  }

  private static void countAvg() {
    double avg = IntStream.range(0, 100).boxed().collect(Collectors.averagingInt(item -> item));
    System.out.println(avg);
  }
}
