package lambda_expression;

public class Lambda4 {
  static int outerStaticNum;
  int outerNum;

  void testScopes() {
    Converter<Integer, String> stringConverter1 = (from) -> {
      outerNum = 23;
      System.out.println(outerNum);
      return String.valueOf(from);
    };

    Converter<Integer, String> stringConverter2 = (from) -> {
      outerStaticNum = 72;
      System.out.println(outerStaticNum);
      return String.valueOf(from);
    };
    System.out.println(outerNum);
    System.out.println(outerStaticNum);
    System.out.println(stringConverter1.convert(1));
    System.out.println(stringConverter2.convert(1));
  }

  public static void main(String[] args) {
    new Lambda4().testScopes();

  }
}
