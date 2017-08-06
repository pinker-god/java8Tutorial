package lambda_expression;

/**
 * Created by xd031 on 2017/8/6.
 */
@FunctionalInterface
public interface Converter1<F, T> {
  F convert(T from);
}
