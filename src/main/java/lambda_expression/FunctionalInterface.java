package lambda_expression;

import method_reference.People;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by xd031 on 2017/8/7.
 */
public class FunctionalInterface {
  public static void main(String[] args) {
    Predicate<Boolean> predicate = Objects::isNull;
    Predicate<Boolean> predicate1 = Objects::nonNull;
    //无参构造器
    Supplier<People> supplier = People::new;
    People people = supplier.get();
    //Cosumer
    Consumer<People> greeter = (p) -> System.out.println("hello!\t" + p.firstName);
    greeter.accept(new People("hs", "ys"));
    //Optionals可以看出filter中Predicate为false的时候被过滤!
    Optional optional = Optional.ofNullable(1).filter((x) -> x > 0);
    System.out.println(optional);
    System.out.println(optional.get());//Supplier

    //map

    People p = new People(null, "pinker");
    Optional<People> p1 = Optional.of(p);
    Optional<String> name = p1.map(p2 -> p2.firstName);
    int len = name.map(s -> {
        System.out.println("我在map里面");
        return s.length();
      }
    ).orElse(100);
    System.out.println(name);
//    System.out.println(name.get());
    System.out.println(len);
  }
}
