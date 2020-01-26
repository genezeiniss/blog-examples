package com.example.gene_test;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class StreamUtility {

    public static <T, E extends RuntimeException> BinaryOperator<T>
    toSingleElementOrElseThrowException(Supplier<E> exception) {
        return (element, otherElement) -> {
            throw exception.get();
        };
    }
}
