/*
package method_reference;

import lambda_expression.Converter;

*/
/**
 * Created by xd031 on 2017/8/7.
 *//*

public class Demo {

  public static void main(String[] args) {
    int num = 1;
    Converter<Integer, String> stringConverter = (from -> String.valueOf(from + num));
    String result = stringConverter.convert(2);
    System.out.println(result);
    Converter<Integer, String> stringConverter1 = new Converter<Integer, String>() {
    int num3=1;
      @Override
      public String convert(Integer from) {
        return String.valueOf(from + num3);
      }
    };
    String result1 = stringConverter1.convert(2);
    System.out.println(result1);
    int num1 = 1;
    Converter<Integer, String> stringConverter2 =
      (from) -> String.valueOf(from + num1);
    String result2 = stringConverter.convert(2);
  }

  private static void method1() {
    PeopleFactory<People> factory = People::new;
    People people = factory.create("hs", "pinker");
  }


}
*/
