package lambda_expression;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface Converter1<F, T> {
  F convert(T from);
}
