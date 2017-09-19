package lambda_expression;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by xd031 on 2017/8/7.
 * ctrl+p对需要的参数提示!
 */
public class OptionalDemo {
  public static void main(String[] args) {
//    testOptitional();
//    new OptionalDemo().testOptitional2(12);
    String name = Student.create(Student::new).map(student -> student.getName()).map(n -> n += "hs").orElse("default");
    String name1 = Student.create(Student::new).map(student -> student.getName()).map(n -> n += "hs").orElseGet(() -> "default get");
    System.out.println(name + "->" + name1);
  }

  private void testOptitional2(int age) {
    User user = new User();
    Optional<User> users = Optional.ofNullable(user);
    String na = users.map(u -> u.getName()).map(name -> name.toLowerCase()).orElse(" ");
    System.out.println(na);
  }

  private static void testOptitional() {
    String[] arr = new String[]{"Lamurudu", "Okanbi", "Oduduwa"};
    Optional<String> n = Arrays.stream(arr).filter(name -> name.startsWith("O")).findFirst();
    System.out.println(n.orElse("lkg"));
    Arrays.stream(arr).filter(name -> name.startsWith("O")).findFirst().ifPresent(name ->
    {
      String s = name.toUpperCase();
      System.out.println("the longest name is:" + s);
    });
    String m = n.orElse("hs");
    System.out.println(m);
  }

  class User {
    private String name;
    private int age;

    public User() {
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }


}
