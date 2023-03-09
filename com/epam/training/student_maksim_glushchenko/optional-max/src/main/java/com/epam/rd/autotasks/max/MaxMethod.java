package com.epam.rd.autotasks.max;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MaxMethod {
    public static OptionalInt max(int[] values) {
        if(values==null){
            return OptionalInt.empty();
        }
        OptionalInt max =IntStream.of(values).max();
        return  max;
    }
}
