package com.manish.vectorapi;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


// VM option: --add-modules jdk.incubator.vector --enable-preview
public class BenchmarkVector {

    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

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
    public static float[] addTwoVectorArrays() {
        var v1 = FloatVector.fromArray(SPECIES, array1, 0);
        var v2 = FloatVector.fromArray(SPECIES, array2, 0);
        var result = v1.add(v2);
        return result.toArray();
    }
}
