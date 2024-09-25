package util;

import java.util.Random;

public class GenRandom {

    public static long seed = 1234;
    public static Random random = new Random(seed);

    public static double randomDouble(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }
}
