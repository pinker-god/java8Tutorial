package lambda_expression;

import java.util.Arrays;
import java.util.List;

public class Demo1 {
  public static void main(String[] args) {
//    testListSortWithLambda();
    testCustomMethod();
  }

  /**
   * * 以前只用Collections的sort方法,原来List也有一个sort方法了(Java8新加的!)
   */


  public static void testListSortWithLambda() {
    List<String> list = Arrays.asList("peter", "anna", "mike", "xenia");
    System.out.println(list);
    list.sort((x, y) -> x.compareTo(y));
    System.out.println(list);
  }

/*
  *自定义函数式接口
 */


  private static void testCustomMethod() {
    Converter<String, Integer> convert = (x) -> Integer.valueOf(x);
    Integer num = convert.convert("123");
    System.out.println(num);
    Converter<String, Integer> convert1 = Integer::valueOf;
    Integer num1 = convert1.convert("123456");
    System.out.println(num1);

    Converter1<String, Integer> converter = (x) -> String.valueOf(x);
    String num2 = converter.convert(123);
    System.out.println(num2);
    Converter1<String, Integer> converter1 = String::valueOf;
    String num3 = converter1.convert(12345);
    System.out.println(num3);
  }
}
