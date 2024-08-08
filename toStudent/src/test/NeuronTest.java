package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import container.Neuron;
import util.GenRandom;

public class NeuronTest {

    Neuron neuron;
    private final static float DELTA = 0.0001f;

    @Test
    public void constructorWeightedTest() {
        int len = 4;
        double[] weight = new double[len];

        for (int i = 0; i < len; i++) {
            weight[i] = GenRandom.randomDouble(-1, 1);
        }

        Neuron neuron = new Neuron(weight, 0);

        assertArrayEquals(weight, neuron.getWeights(), DELTA);
        assertEquals(0, neuron.getBias(), DELTA);
        assertEquals(0, neuron.getValue(), DELTA);
        assertEquals(0, neuron.getGradient(), DELTA);
    }

    @Test
    public void constructorValueTest() {
        double value = 0.7;

        Neuron neuron = new Neuron(value);

        assertNull(neuron.getWeights());
        assertEquals(value, neuron.getValue(), DELTA);
        assertEquals(-1, neuron.getBias(), DELTA);
        assertEquals(-1, neuron.getGradient(), DELTA);
    }

    @Test
    public void weightRangeTest() { // Is static modifier
        Neuron.setRangeWeight(-2, -1);
        assertEquals(-2, Neuron.getMinWeightValue(), DELTA);
        assertEquals(-1, Neuron.getMaxWeightValue(), DELTA);

        Neuron.setRangeWeight(-1, 1);
        assertEquals(-1, Neuron.getMinWeightValue(), DELTA);
        assertEquals(1, Neuron.getMaxWeightValue(), DELTA);
    }

    @Test
    public void getAndSetWeightsTest() {
        double[] weights = {0.1, 0.2, 0.3};

        neuron = new Neuron(0.5);
        neuron.setWeights(weights);

        assertArrayEquals(weights, neuron.getWeights(), DELTA);
    }

    @Test
    public void getAndSetBiasTest() {
        double bias = -0.3;

        neuron = new Neuron(0.5);
        neuron.setBias(bias);

        assertEquals(bias, neuron.getBias(), DELTA);
    }

    @Test
    public void getAndSetValueTest() {
        double value = 0.8;

        neuron = new Neuron(0.5);
        neuron.setValue(value);

        assertEquals(value, neuron.getValue(), DELTA);
    }

    @Test
    public void getAndSetGradientTest() {
        double gradient = -0.2;

        neuron = new Neuron(0.5);
        neuron.setGradient(gradient);

        assertEquals(gradient, neuron.getGradient(), DELTA);
    }

    @Test
    public void constructorEmptyWeightsTest() {
        double[] weights = {};

        neuron = new Neuron(weights, 0.5);

        assertArrayEquals(weights, neuron.getWeights(), DELTA);
    }

    @Test
    public void constructorNegativeBiasTest() {
        double bias = -0.7;

        neuron = new Neuron(new double[]{0.1, 0.2}, bias);

        assertEquals(bias, neuron.getBias(), DELTA);
    }

    @Test
    public void constructorNegativeValueTest() {
        double value = -0.5;

        neuron = new Neuron(value);

        assertEquals(value, neuron.getValue(), DELTA);
    }

}
