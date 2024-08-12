package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import container.Neuron;
import util.GenRandom;

public class NeuronTest {

    Neuron neuron;
    private final static double DELTA = 0.0001;

    @Test
    public void testConstructorWeighted() {
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
    public void testConstructorValue() {
        double value = 0.7;

        Neuron neuron = new Neuron(value);

        assertNull(neuron.getWeights());
        assertEquals(value, neuron.getValue(), DELTA);
        assertEquals(-1, neuron.getBias(), DELTA);
        assertEquals(-1, neuron.getGradient(), DELTA);
    }

    @Test
    public void testWeightRange() {

        Neuron.setRangeWeight(-2, -1);
        assertEquals(-2, Neuron.getMinWeightValue(), DELTA);
        assertEquals(-1, Neuron.getMaxWeightValue(), DELTA);

        Neuron.setRangeWeight(-1, 0);
        assertEquals(-1, Neuron.getMinWeightValue(), DELTA);
        assertEquals(0, Neuron.getMaxWeightValue(), DELTA);
    }

    @Test
    public void testGetAndSetWeights() {
        double[] weights = {0.1, 0.2, 0.3};

        neuron = new Neuron(0.5);
        neuron.setWeights(weights);

        assertArrayEquals(weights, neuron.getWeights(), DELTA);
    }

    @Test
    public void testGetAndSetBias() {
        double bias = -0.3;

        neuron = new Neuron(0.5);
        neuron.setBias(bias);

        assertEquals(bias, neuron.getBias(), DELTA);
    }

    @Test
    public void testGetAndSetValue() {
        double value = 0.8;

        neuron = new Neuron(0.5);
        neuron.setValue(value);

        assertEquals(value, neuron.getValue(), DELTA);
    }

    @Test
    public void testGetAndSetGradient() {
        double gradient = -0.2;

        neuron = new Neuron(0.5);
        neuron.setGradient(gradient);

        assertEquals(gradient, neuron.getGradient(), DELTA);
    }

    @Test
    public void testConstructorEmptyWeights() {
        double[] weights = {};

        neuron = new Neuron(weights, 0.5);

        assertArrayEquals(weights, neuron.getWeights(), DELTA);
    }

    @Test
    public void testConstructorNegativeBias() {
        double bias = -0.7;

        neuron = new Neuron(new double[]{0.1, 0.2}, bias);

        assertEquals(bias, neuron.getBias(), DELTA);
    }

    @Test
    public void testConstructorNegativeValue() {
        double value = -0.5;

        neuron = new Neuron(value);

        assertEquals(value, neuron.getValue(), DELTA);
    }

}
