package lambda_expression;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface Converter<F, T> {
  T convert(F from);
}
