package method_reference;

import java.time.LocalDate;

/**
 * Created by xd031 on 2017/8/6.
 */
public class Person {
  private String name;
  private LocalDate birthDay;

  public Person(String name, LocalDate birthDay) {
    this.name = name;
    this.birthDay = birthDay;
  }

  public static int compareByAge(Person a, Person b) {
    return a.birthDay.compareTo(b.birthDay);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(LocalDate birthDay) {
    this.birthDay = birthDay;
  }

  @Override
  public String toString() {
    return "Person{" +
      "name='" + name + '\'' +
      '}';
  }
}
