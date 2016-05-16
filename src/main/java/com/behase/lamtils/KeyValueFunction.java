package com.behase.lamtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public interface KeyValueFunction<T> extends Serializable, Function<String, T> {
    default SerializedLambda serialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda)replaceMethod.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Class<?> getContainingClass() {
        try {
            String className = serialized().getImplClass().replaceAll("/", ".");
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Method method() {
        SerializedLambda lambda = serialized();
        Class<?> containingClass = getContainingClass();
        return Arrays.stream(containingClass.getDeclaredMethods())
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(UnableToGuessMethodException::new);
    }

    default Parameter parameter(int n) {
        return method().getParameters()[n];
    }

    default int getParameterSize() {
        return method().getParameters().length;
    }

    default void checkParametersEnabled() {
        if (Objects.equals("arg0", parameter(0).getName())) {
            throw new IllegalStateException(
                    "You need to compile with javac -parameters for parameter reflection to work; You also need java " +
                            "8u60 or newer to use it with lambdas");
        }
    }

    default String key() {
        checkParametersEnabled();

        int parameterSize = getParameterSize();
        if (parameterSize == 2) {
            return parameter(1).getName();
        } else if (parameterSize == 1) {
            return parameter(0).getName();
        } else {
            throw new IllegalStateException("Invalid parameter size.");
        }
    }

    default T value() {
        return apply(key());
    }

    class UnableToGuessMethodException extends RuntimeException {
    }
}
