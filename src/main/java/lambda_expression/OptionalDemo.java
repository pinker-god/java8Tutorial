package lambda_expression;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by xd031 on 2017/8/7.
 */
public class OptionalDemo {
  public static void main(String[] args) {
    String[] arr = new String[]{"Lamurudu", "Okanbi", "Oduduwa"};
    Optional<String> n = Arrays.stream(arr).filter(name -> name.startsWith("O")).findFirst();
    System.out.println(n.get());
    Arrays.stream(arr).filter(name -> name.startsWith("E")).findFirst().ifPresent(name ->
    {
      String s = name.toUpperCase();
      System.out.println("the longest name is:" + s);
    });
    String m=n.orElse("hs");
   
    System.out.println(m);
//    m=n.orElseGet()
  }
}
