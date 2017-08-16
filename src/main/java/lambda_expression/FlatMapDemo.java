package lambda_expression;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by xd031 on 2017/8/16.
 */
public class FlatMapDemo {
  public static void main(String[] args) {
//    method1();
    method2();
  }

  private static void method2() {
    Student obj1 = new Student();
    obj1.setName("hs");
    obj1.addBook("Java8 in action");
    obj1.addBook("Spring in Action");
    obj1.addBook("Boot in Action");
    Student obj2 = new Student();
    obj2.setName("ys");
    obj2.addBook("Scala in Action");
    obj2.addBook("Haskel in Action");
    obj2.addBook("Spring in Action");
    List<Student> list = Arrays.asList(obj1, obj2);
    list.stream().map(stu -> stu.getBook()).flatMap(book -> book.stream()).forEach(System.out::println);
    list.stream().map(stu -> stu.getBook()).flatMap(book -> book.stream()).distinct().forEach(System.out::println);
  }


  private static void method1() {
    String data[][] = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
    Stream<String[]> temp = Arrays.stream(data);
    Stream<String> temp1 = temp.flatMap(x -> Arrays.stream(x));
    Stream<String> temp2 = temp1.filter(x -> !"a".equals(x.toString()));
    temp2.forEach(System.out::println);
    Arrays.stream(data)
      .flatMap(x -> Arrays.stream(x))
      .filter(x -> !"a".equals(x))
      .forEach(System.out::println);
  }
}
