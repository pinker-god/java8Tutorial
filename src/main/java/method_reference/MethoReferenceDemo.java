package method_reference;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;

/**
 * Created by xd031 on 2017/8/6.
 */
public class MethoReferenceDemo {
  public static void main(String[] args) {
    Person[] persons = new Person[]{
      new Person("hs", LocalDate.of(1992, 12, 26)),
      new Person("ys", LocalDate.of(1991, 12, 26)),
      new Person("ws", LocalDate.of(1992, 11, 26)),
      new Person("zs", LocalDate.of(1992, 9, 26)),
    };
//    noUseLambda(persons);
//    useLambdaExpression(persons);
//    useLambdaMehodReference(persons);
//    useLambdaStaticMethodReference(persons);
    useLambdaWithComparator(persons, Comparator.comparing(Person::getBirthDay));
//    useLambdaWithFactory(persons);
  }

  /**
   * 默认降序,此处来个升序
   * @param persons
   */
  private static void useLambdaWithFactory(Person[] persons) {
    useLambdaWithComparator(persons, comparing(Person::getBirthDay).reversed());
    System.out.println("MethoReferenceDemo.useLambdaWithFactory");
    System.out.println(Arrays.asList(persons));
  }

  private static void useLambdaWithComparator(Person[] persons, Comparator<Person> comparing) {
    Arrays.sort(persons, comparing);
    System.out.println("MethoReferenceDemo.useLambdaWithComparator");
    System.out.println(Arrays.asList(persons));
  }

  private static void useLambdaStaticMethodReference(Person[] persons) {
    Arrays.sort(persons, Person::compareByAge);
    System.out.println("MethoReferenceDemo.useLambdaStaticMethodReference");
    System.out.println(Arrays.toString(persons));
  }

  /**
   * 这个方法式静态的,其实就是静态方法引用,这么写貌似不太好!
   * @param persons
   */
  private static void useLambdaMehodReference(Person[] persons) {
    Arrays.sort(persons, (p1, p2) -> Person.compareByAge(p1, p2));
    System.out.println("MethoReferenceDemo.useLambdaMehodReference");
    System.out.println(Arrays.asList(persons));
  }

  private static void useLambdaExpression(Person[] persons) {
    Arrays.sort(persons, (p1, p2) -> p1.getBirthDay().compareTo(p2.getBirthDay()));
    System.out.println("MethoReferenceDemo.useLambdaExpression");
    System.out.println(Arrays.toString(persons));
  }

  /**
   * 原始的写法,用匿名内部类
   *
   * @param persons
   */
  public static void noUseLambda(Person[] persons) {
    Arrays.sort(persons, new Comparator<Person>() {
      @Override
      public int compare(Person o1, Person o2) {
        return o1.getBirthDay().compareTo(o2.getBirthDay());
      }
    });
    System.out.println("MethoReferenceDemo.noUseLambda");
    System.out.println(Arrays.asList(persons));
  }

}
