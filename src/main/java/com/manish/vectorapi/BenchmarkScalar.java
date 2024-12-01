package com.manish.vectorapi;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BenchmarkScalar {
    static int size = 10_000_000; // Large dataset
    static float[] array1 = new float[size];
    static float[] array2 = new float[size];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < size; i++) {
            array1[i] = i * 1.0f;
            array2[i] = i * 2.0f;
        }
        org.openjdk.jmh.Main.main(args);

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5, time = 100, timeUnit =  TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 100, timeUnit =  TimeUnit.MILLISECONDS)
    public static float[] addTwoScalarArrays() {
        float[] result = new float[array1.length];
        for(int i = 0; i< array1.length; i++) {
            result[i] = array1[i] + array2[i];
        }
        return result;
    }
}
