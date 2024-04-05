package test;

import container.Data;
import container.Network;
import function.Function;
import org.junit.Before;
import org.junit.Test;
import util.GenRandom;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NetworkTest { // Ignore static inferences

    private static final double DELTA = 0.01;

    private Network network;

    @Before
    public void setUp() {
        GenRandom.random = new Random(42);
        int[] nLayers = {2, 8, 1};
        Function[] functions = {Function.SIGMOID, Function.SIGMOID};
        Data[] data = new Data[]{
                new Data(new double[]{0, 0}, new double[]{0}),
                new Data(new double[]{0, 1}, new double[]{1}),
                new Data(new double[]{1, 0}, new double[]{1}),
                new Data(new double[]{1, 1}, new double[]{0})
        };
        network = new Network(nLayers, functions, data);
    }

    @Test
    public void testCustomConstructor() {
        int[] nLayers = {3, 5, 2};
        Function[] functions = {Function.SIGMOID, Function.RELU};
        Data[] data = new Data[]{
                new Data(new double[]{1, 2, 3}, new double[]{0.5, 0.6}),
                new Data(new double[]{2, 3, 4}, new double[]{0.3, 0.4})
        };
        Network customNetwork = new Network(nLayers, functions, data);

        assertNotNull(customNetwork);
        assertNotNull(customNetwork.getLayers());
        assertNotNull(customNetwork.getDatasets());
        assertEquals(3, customNetwork.getLayers().length);
        assertEquals(2, customNetwork.getDatasets().length);
    }

    @Test
    public void testCustomAdditionalLayerFunctions() {
        int[] nLayers = {3, 101, 1001, 28, 2};
        Function[] functions = {Function.TANH, Function.RELU, Function.TANH, Function.RELU};
        Data[] data = new Data[]{
                new Data(new double[]{1, 2, 3}, new double[]{0.5, 0.6}),
                new Data(new double[]{2, 3, 4}, new double[]{0.3, 0.4})
        };
        Network customNetwork = new Network(nLayers, functions, data);

        assertNotNull(customNetwork);
        assertNotNull(customNetwork.getLayers());
        assertNotNull(customNetwork.getDatasets());
        assertEquals(5, customNetwork.getLayers().length);
        assertEquals(2, customNetwork.getDatasets().length);

        assertEquals(Function.TANH, customNetwork.getLayers()[1].getFunction());
        assertEquals(Function.RELU, customNetwork.getLayers()[4].getFunction());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(network);
        assertNotNull(network.getLayers());
        assertNotNull(network.getDatasets());
        assertEquals(3, network.getLayers().length);
        assertEquals(4, network.getDatasets().length);
    }

    @Test
    public void testForward() {
        double[] inputs = {0, 0};
        network.forward(inputs);
        assertEquals(3, network.getLayers().length);
        assertEquals(0.521111338414437, network.getLayers()[2].getNeurons()[0].getValue(), DELTA);
    }

    @Test
    public void testBackward() {
        double[] inputs = {0, 0};
        double learningRate = 0.5;
        Data tData = new Data(new double[]{1, 1}, new double[]{0});
        network.forward(inputs);
        network.backward(learningRate, tData);
        assertEquals(0.4551273600657362, network.getLayers()[1].getNeurons()[0].getWeights()[0], DELTA);
        assertEquals(0.36644694351969087, network.getLayers()[1].getNeurons()[0].getWeights()[1], DELTA);
    }

    @Test
    public void testSumGradient() {
        network.forward(new double[]{1, 0});
        double sumGradient = network.sumGradient(0, 2);
        assertEquals(0, sumGradient, 0.01);
    }

    @Test
    public void testTrain() {
        network.train(1000000, 0.05);
        network.forward(new double[]{0, 0});
        assertEquals(0, network.getLayers()[2].getNeurons()[0].getValue(), DELTA);
    }

    @Test
    public void testCalculateLoss() {
        network.forward(new double[]{0, 0});
        double loss = network.calculateLoss(network.getLayers()[2].getNeurons(), new double[]{0});
        assertEquals(0.3484174199003581, loss, DELTA);
    }

}
