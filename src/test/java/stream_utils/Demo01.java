package stream_utils;

import com.codepoetics.protonpack.StreamUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by xd031 on 2017/9/21.
 */
public class Demo01 {
  @Test
  public void test() {
    Stream<Integer> infiniteInts = Stream.iterate(0, i -> i + 1);
    Stream<Integer> finiteInts = StreamUtils.takeWhile(infiniteInts, i -> i < 10);
    assertThat(finiteInts.collect(Collectors.toList()), hasSize(10));
  }


  //zip:Combines two streams using the supplied combiner function
  @Test
  public void test1() {
    Stream<String> streamA = Stream.of("A", "B", "C");
    Stream<String> streamB = Stream.of("Apple", "Banana", "Carrot", "Doughnut");

    List<String> zipped = StreamUtils.zip(streamA,
      streamB,
      (a, b) -> a + " is for " + b)
      .collect(Collectors.toList());
    assertThat(zipped, contains("A is for Apple", "B is for Banana", "C is for Carrot"));
  }

  @Test
  public void test2() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\java\\stream_utils\\Demo01.java")));
    reader.lines().flatMap(line -> Arrays.stream(line.split("[^a-zA-Z]")))
      .filter(word -> word.length() > 0).map(word -> new SimpleEntry<>(word, 1))
      .collect(groupingBy(SimpleEntry::getKey, counting())).forEach((k, value) -> System.out.println(String.format("%s ==>> %d", k, value)));
  }

  @Test
  public void test3() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\java\\stream_utils\\Demo01.java")));
    reader.lines().flatMap(line -> Arrays.stream(line.split("[^a-zA-Z]")))
      .filter(word -> word.length() > 0).map(word -> new SimpleEntry<>(word, 1))
      .collect(toMap(SimpleEntry::getKey, SimpleEntry::getValue, (v1, v2) -> v1 + v2)).forEach((k, value) -> System.out.println(String.format("%s ==>> %d", k, value)));
  }

  @Test
  public void test4() throws FileNotFoundException {
    new BufferedReader(new FileReader(new File("src\\test\\java\\stream_utils\\Demo01.java")))
      .lines()
      .flatMap(line -> Arrays.stream(line.split("[^a-zA-Z]")))
      .filter(word -> word.length() > 0)
      .map(word -> new SimpleEntry<>(word, 1))
      .reduce(new LinkedHashMap<String, Integer>(), (map, entry) ->
      {
        map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + entry.getValue());
        return map;
      }, (map1, map2) -> {
        map1.putAll(map2);
        return map1;
      }).forEach((k, value) -> System.out.println(String.format("%s ==>> %d", k, value)));
  }

  @Test
  public void test5() {
    HashMap<String, Integer> map = new HashMap();
    List<SimpleEntry<String, Integer>> list = Arrays.asList(
      new SimpleEntry<String, Integer>("hs", 1),
      new SimpleEntry<String, Integer>("ys", 1),
      new SimpleEntry<String, Integer>("ws", 1),
      new SimpleEntry<String, Integer>("ws", 1),
      new SimpleEntry<String, Integer>("ws", 1),
      new SimpleEntry<String, Integer>("hs", 5)
    );
    for (SimpleEntry<String, Integer> entry : list) {
      map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + entry.getValue());
    }

    map.forEach((key, value) -> System.out.println(key + ":" + value));
  }

  @Test
  public void test6() {
    foriCompute();
    streamCompute();
    forkJoinCompute();
  }


  private void forkJoinCompute() {
    Instant start = Instant.now();
    ForkJoinPool pool = new ForkJoinPool();
    ForkJoinTask<Long> task = new ForkJoinCompute(0, 100000000000L);
    long sum = pool.invoke(task);
    Instant end = Instant.now();
    System.out.println("forkjoin\t" + Duration.between(start, end).toMillis());//forkjoin	52247
  }

  class ForkJoinCompute extends RecursiveTask<Long> {
    private long start;
    private long end;
    private static final long THRESHOLD = 10000;

    public ForkJoinCompute(long start, long end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected Long compute() {
      if (end - start <= THRESHOLD) {
        long sum = 0;
        for (long i = start; i <= end; i++) {
          sum += i;
        }
        return sum;
      } else {
        long mid = (end + start) / 2;
        ForkJoinCompute left = new ForkJoinCompute(start, mid);
        left.fork();
        ForkJoinCompute right = new ForkJoinCompute(mid + 1, end);
        right.fork();
        return left.join() + right.join();
      }
    }
  }

  private void foriCompute() {
    Instant start = Instant.now();
    long sum = 0;
    for (long i = 0; i < 100000000000L; i++) {
      sum += i;
    }
    Instant end = Instant.now();
    System.out.println("fori\t" + Duration.between(start, end).toMillis());//fori 61161
  }

  private void streamCompute() {
    Instant start;
    Instant end;
    start = Instant.now();
    LongStream.range(0, 100000000000L).parallel().sum();
    end = Instant.now();
    System.out.println("stream\t" + Duration.between(start, end).toMillis());//stream 38289
  }
}
