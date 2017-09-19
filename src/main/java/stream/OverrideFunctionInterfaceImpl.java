package stream;

/**
 * Created by xd031 on 2017/9/19.
 */
public class OverrideFunctionInterfaceImpl implements MyFunctionInterface<String> {
  @Override
  public void test(String s) {
    System.out.println("i am in override");
  }

  @Override
  public void sayHello(String name) {
    System.out.println("override");
  }
}
