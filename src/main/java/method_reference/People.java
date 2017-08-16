package method_reference;

/**
 * Created by xd031 on 2017/8/7.
 */
public class People {
  public String firstName;
  public String lastName;

  public People() {
    System.out.println("我是无参构造器!");
  }

  public People(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
