package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import container.Neuron;
import util.GenRandom;

public class NeuronTest {

    Neuron neuron;

    @Test
    public void constructorWeightedTest() {
        int len = 4;
        double[] weight = new double[len];

        for (int i = 0; i < len; i++) {
            weight[i] = GenRandom.randomDouble(-1, 1);
        }

        Neuron neuron = new Neuron(weight, 0);

        assertArrayEquals(weight, neuron.getWeights(), 0.0001);
        assertArrayEquals(weight, neuron.getCacheWeights(), 0.0001);
        assertEquals(0, neuron.getBias(), 0.0001);
        assertEquals(0, neuron.getValue(), 0.0001);
        assertEquals(0, neuron.getGradient(), 0.0001);
    }

    @Test
    public void constructorValueTest() {
        double value = 0.7;

        Neuron neuron = new Neuron(value);

        assertNull(neuron.getWeights());
        assertNull(neuron.getCacheWeights());
        assertEquals(value, neuron.getValue(), 0.0001);
        assertEquals(-1, neuron.getBias(), 0.0001);
        assertEquals(-1, neuron.getGradient(), 0.0001);
    }

    @Test
    public void rangeWeightSet() {
        double min = -0.5;
        double max = 0.5;

        Neuron.setRangeWeight(min, max);

        assertEquals(min, Neuron.getMinWeightValue(), 0.0001);
        assertEquals(max, Neuron.getMaxWeightValue(), 0.0001);
    }

    @Test
    public void getAndSetWeightsTest() {
        double[] weights = {0.1, 0.2, 0.3};

        neuron = new Neuron(0.5);
        neuron.setWeights(weights);

        assertArrayEquals(weights, neuron.getWeights(), 0.0001);
    }

    @Test
    public void getAndSetCacheWeightsTest() {
        double[] cacheWeights = {0.3, 0.2, 0.1};

        neuron = new Neuron(0.5);
        neuron.setCacheWeights(cacheWeights);

        assertArrayEquals(cacheWeights, neuron.getCacheWeights(), 0.0001);
    }

    @Test
    public void getAndSetBiasTest() {
        double bias = -0.3;

        neuron = new Neuron(0.5);
        neuron.setBias(bias);

        assertEquals(bias, neuron.getBias(), 0.0001);
    }

    @Test
    public void getAndSetValueTest() {
        double value = 0.8;

        neuron = new Neuron(0.5);
        neuron.setValue(value);

        assertEquals(value, neuron.getValue(), 0.0001);
    }

    @Test
    public void getAndSetGradientTest() {
        double gradient = -0.2;

        neuron = new Neuron(0.5);
        neuron.setGradient(gradient);

        assertEquals(gradient, neuron.getGradient(), 0.0001);
    }

    @Test
    public void constructorEmptyWeightsTest() {
        double[] weights = {};

        neuron = new Neuron(weights, 0.5);

        assertArrayEquals(weights, neuron.getWeights(), 0.0001);
        assertArrayEquals(weights, neuron.getCacheWeights(), 0.0001);
    }

    @Test
    public void constructorNegativeBiasTest() {
        double bias = -0.7;

        neuron = new Neuron(new double[]{0.1, 0.2}, bias);

        assertEquals(bias, neuron.getBias(), 0.0001);
    }

    @Test
    public void constructorNegativeValueTest() {
        double value = -0.5;

        neuron = new Neuron(value);

        assertEquals(value, neuron.getValue(), 0.0001);
    }

}
