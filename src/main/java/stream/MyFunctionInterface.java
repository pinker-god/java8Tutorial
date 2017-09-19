package stream;

import java.util.function.Supplier;

/**
 * Created by xd031 on 2017/9/19.
 */
@FunctionalInterface
public interface MyFunctionInterface<T> {

  void test(T t);

  default void sayHello(String name) {
    System.out.println("hello\t" + name);
  }

  static MyFunctionInterface create(Supplier<MyFunctionInterface> supplier) {
    return supplier.get();
  }
}
