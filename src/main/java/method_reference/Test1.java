package method_reference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xd031 on 2017/8/19.
 */
public class Test1 {
  public static void main(String[] args) {
    List<String> aList = new ArrayList<String>();
    List<? extends Object> covariantList = aList;
    List<? super String> contravariantList = aList;
//    covariantList.add("d"); //wrong
    Object a = covariantList.get(0);
    contravariantList.add("d"); //OK
//    String b = contravariantList.get(1); //wrong
    Object c = contravariantList.get(2);
  }

  //surper确定下界-->可以put
  public void testSuper(List<? super Parent> list) {
    list.add(new GrandSon());
    list.add(new Parent());
  }

  //extends确定上界-->可以get
  public void testExtends(List<? extends Parent> list) {
    Parent x = list.get(0);
  }
}

class GrandFather {
}

class Parent extends GrandFather {
}

class Child extends Parent {
}

class GrandSon extends Child {
}
