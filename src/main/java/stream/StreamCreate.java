package stream;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xd031 on 2017/9/19.
 */
public class StreamCreate {
  public static void main(String[] args) throws FileNotFoundException {
    //1. 通过集合的stream()方法或者parallelStream()
    Stream<Integer> stream = Arrays.asList(1, 2, 3, 4).stream();
    Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4).parallelStream();

    //2. 数组创建stream
    Arrays.stream(new String[]{"hs", "ys", "zs"});

    //3. 静态流的方式创建Stream.generate;Stream.();
    IntStream.of(new int[]{1, 2, 3, 4}).mapToObj(item -> item + "\t").forEach(System.out::print);
    System.out.println();
    Stream.of("hs", "ys").map(item -> item + "\t").forEach(System.out::print);
    System.out.println();
    IntStream.range(0, 100).mapToDouble(item -> item * 1.0).boxed().collect(Collectors.toList()).stream().map(item -> item + "\t").forEach(System.out::print);
    System.out.println();
    Arrays.asList(1, 2, 3, 4).stream().map(item -> item + "\t").forEach(System.out::print);
    System.out.println();
    //2	6	18	54	162	486	1458	4374	13122	39366产生迭代的无线数据
    Stream.iterate(2, n -> n * 3).limit(10).map(item -> item + "\t").forEach(System.out::print);

    //文件流中读取
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src\\main\\java\\stream\\StreamCreate.java")));
//    bufferedReader.lines().flatMap(line -> Arrays.stream(line.split(" +"))).map(word -> new SimpleEntry<>(word, 1)).sorted((ele1, ele2) -> ele1.getKey().compareTo(ele2.getKey())).reduce()
//    bufferedReader.lines().flatMap(line -> Arrays.stream(line.split(" +"))).map(word -> new SimpleEntry<>(word, 1)).sorted(Comparator.comparing(SimpleEntry::getKey)).reduce();
    test();
    testPeek();
    testReduce();
    testCollectToMap();
    testCollectToMap1();
    testCollectToMap2();
    testJoin();
  }

  private static void testJoin() {
    System.out.println(Arrays.asList("hs", "ys", "zs").stream().collect(Collectors.joining("\t")));
  }


  private static void testCollectToMap2() {
    Arrays.asList(
      new God("hs", 100),
      new God("hs1", 100),
      new God("hs2", 100),
      new God("hs3", 100),
      new God("ys", 99),
      new God("ys1", 99),
      new God("ys2", 99),
      new God("ws", 98),
      new God("ws1", 98),
      new God("ws2", 98),
      new God("ws3", 98),
      new God("ws4", 98),
      new God("zs", 97)
    ).stream().collect(Collectors.groupingBy(God::getPoint, Collectors.mapping(God::getName, Collectors.toList()))).forEach((key, value) -> System.out.println(key + ":" + value));
  }

  private static void testCollectToMap1() {
    Arrays.asList(
      new God("hs", 100),
      new God("hs1", 100),
      new God("hs2", 100),
      new God("hs3", 100),
      new God("ys", 99),
      new God("ys1", 99),
      new God("ys2", 99),
      new God("ws", 98),
      new God("ws1", 98),
      new God("ws2", 98),
      new God("ws3", 98),
      new God("ws4", 98),
      new God("zs", 97)
    ).stream().collect(Collectors.groupingBy(God::getPoint, Collectors.toList())).forEach((key, value) -> System.out.println(key + ":" + value));
  }


  private static void testCollectToMap() {
    Arrays.asList(
      new God("hs", 100),
      new God("hs1", 100),
      new God("hs2", 100),
      new God("hs3", 100),
      new God("ys", 99),
      new God("ys1", 99),
      new God("ys2", 99),
      new God("ws", 98),
      new God("ws1", 98),
      new God("ws2", 98),
      new God("ws3", 98),
      new God("ws4", 98),
      new God("zs", 97)
    ).stream().collect(HashMap<Integer, List<God>>::new, (map, god) -> {
      List<God> list = Optional.ofNullable(map.get(god.point)).orElseGet(ArrayList::new);
      list.add(god);
      map.put(god.point, list);
    }, HashMap::putAll).forEach((key, value) -> System.out.println(key + ":" + value));
  }

  static class God {
    public String name;
    public int point;

    public God(String name, int point) {
      this.name = name;
      this.point = point;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getPoint() {
      return point;
    }

    public void setPoint(int point) {
      this.point = point;
    }

    @Override
    public String toString() {
      return String.format("[%s,%d]", name, point);
    }
  }

  private static void testReduce() {
    System.out.println();
    System.out.println(Arrays.asList(1, 2, 3, 4, 5).stream().reduce(10, (sum, num2) ->
    {
      System.out.print(sum + ":" + num2 + "\t");
      return sum + num2;
    }));
  }

  //可以看出peek的作用!
  private static void testPeek() {
    Arrays.asList(1, 4, 7, 9, 3).stream().map(item -> item * 2).map(item -> item + "\t").peek(System.out::print).forEach(System.out::print);
    System.out.println("-----------------------------------");
    List<String> list = Arrays.asList(1, 4, 7, 9, 3).stream().map(item -> item * 2).map(item -> item + "\t").peek(System.out::print).collect(Collectors.toList());
    list.forEach(System.out::print);
  }

  private static void test() {
    boolean flag = Arrays.asList(1, 2, 3, 4).stream().anyMatch((item) -> item % 2 == 0);
    boolean flag1 = Arrays.asList(1, 2, 3, 4).stream().allMatch((item) -> item % 2 == 0);
    Optional<Integer> result = Arrays.asList(1, 2, 3, 4).stream().filter((item) -> item % 2 == 0).findAny();
    Optional<Integer> result1 = Arrays.asList(1, 2, 3, 4).stream().filter((item) -> item % 2 == 0).findFirst();
    System.out.println(flag + "\t" + flag1);
    System.out.println(result.orElseGet(() -> 0) + "\t" + result1.orElse(0));
    Optional<Integer> result3 = Arrays.asList(1, 2, 3, 4).stream().max(Integer::compare);
    Optional<Integer> result4 = Arrays.asList(1, 2, 3, 4).stream().min(Integer::compareTo);
    System.out.println(result3.get() + "\t" + result4.get());
  }

}
