package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xd031 on 2017/9/19.
 */
public class Test1 {
  public static void main(String[] args) {
    MyFunctionInterface functionInterface = MyFunctionInterface.create(FunctionInterfaceImpl::new);
    functionInterface.sayHello("ys");
    functionInterface = MyFunctionInterface.create(OverrideFunctionInterfaceImpl::new);
    functionInterface.sayHello("ys");
  }
}
