package stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * Created by xd031 on 2017/9/18.
 */

public class Parallelism {
  public static void main(String[] args) {
//    parallelismAndSequential();
//    ConcurrentModificationExceptionTest();//这个方法是种典型的错误!
//    concurrentDataSource();
//    statefullExpression();
//    validLazyLoad();
//    orderTest();
  }

  private static void orderTest() {
    System.out.println("List");
    List<Integer> list = Arrays.asList(2, 4, 8, 5, 1, 0);
    list.stream().forEach(System.out::print);
    System.out.println("\nArray");
    Integer[] integers = new Integer[]{2, 4, 8, 5, 1, 0};
    Arrays.stream(integers).forEach(System.out::print);
    System.out.println("\nList sorted");
    list.stream().sorted().forEach(System.out::print);
    System.out.println("\nArray sorted");
    Arrays.stream(integers).sorted().forEach(System.out::print);
    System.out.println("\nList unordered");
    list.stream().sorted().unordered().forEach(System.out::print);
    System.out.println("\nArray unordered");
    Arrays.stream(integers).sorted().unordered().forEach(System.out::print);
  }

  private static void statefullExpression() {
    List<String> list = new ArrayList<>(Arrays.asList("hs", "ys"));
    class State {
      boolean s;
    }
    State state = new State();
    //输出hs,ok(预期输出hs,ys的,因为用了有状态的函数,所以中间被修改了)
    Stream<String> stream = list.stream().map(str -> {
      if (state.s)
        return "ok";
      else {
        state.s = true;
        return str;
      }
    });
    stream.forEach(System.out::print);
  }

  //感觉也不好用,因此注意不能在流过程中添加对象.删除能成功,比如用filter其实并没有被删除!
  private static void concurrentDataSource() {
    List<String> stringList = new CopyOnWriteArrayList<>(Arrays.asList("hs", "ys"));
    stringList.stream().map(str -> {
      stringList.add("zs");
      return str;
    }).forEach(System.out::print);
  }

  //Exception in thread "main" java.util.ConcurrentModificationException
  private static void ConcurrentModificationExceptionTest() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    List<Integer> newList = new ArrayList<>(list);
    newList.stream().map(x -> x + 2).forEach(System.out::print);


    //分析下面这端代码,因为在流的管道中对数据源进行修改,no-concurrent.
    List<String> l = new ArrayList(Arrays.asList("one", "two"));
    l.stream().forEach(s -> l.add("three"));
  }

  private static void validLazyLoad() {
    List<String> strings = new ArrayList<>(Arrays.asList("hs", "ys"));
    Stream<String> stream = strings.stream();
    Stream<String> stream1 = stream.map(s -> s += "otk");
    strings.add("zs");
    stream1.forEach(System.out::print);
  }

  private void add(String str) {

  }

  private static void parallelismAndSequential() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    list.stream().forEach(System.out::print);
    System.out.println("\n并行输出");
    list.parallelStream().forEach(System.out::print);
    System.out.println("\n串行转并行");
    list.stream().parallel().forEach(System.out::print);
    System.out.println("\n并行转串行");
    list.parallelStream().sequential().forEach(System.out::print);
  }
}
