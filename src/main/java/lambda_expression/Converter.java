package lambda_expression;

/**
 * Created by xd031 on 2017/8/6.
 */
@FunctionalInterface
public interface Converter<F, T> {
  T convert(F from);
}
