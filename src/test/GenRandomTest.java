package test;

import org.junit.Test;

import java.util.Random;

import util.GenRandom;

import static org.junit.Assert.assertTrue;

public class GenRandomTest {

    @Test
    public void testRandomDoubleInRange() {
        double min = -10;
        double max = 10;
        double randomValue = GenRandom.randomDouble(min, max);
        assertTrue(randomValue >= min && randomValue <= max); // Generated value should be within the range
    }

    @Test
    public void testRandomDistribution() {
        double min = -10;
        double max = 10;
        int iterations = 10000;
        Random random = new Random();
        int countPositive = 0;
        int countNegative = 0;

        for (int i = 0; i < iterations; i++) {
            double randomValue = GenRandom.randomDouble(min, max);
            if (randomValue >= 0) {
                countPositive++;
            } else {
                countNegative++;
            }
        }

        double proportion = (double) countPositive / (double) countNegative;
        assertTrue(proportion > 0.9 && proportion < 1.1); // Positive and negative values should be approximately equal
    }
}

