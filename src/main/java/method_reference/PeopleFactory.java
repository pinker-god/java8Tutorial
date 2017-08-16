package method_reference;

/**
 * Created by xd031 on 2017/8/7.
 */
public interface PeopleFactory<P extends People> {
  P create(String firstName, String lastName);
}
