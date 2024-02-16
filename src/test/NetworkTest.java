package test;

import container.Data;
import container.Network;
import function.Function;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NetworkTest { // Run this file only once

    private static final double DELTA = 0.01;

    private Network network;

    @Before
    public void setUp() {
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
    public void testForward() { // Run only once !
        double[] inputs = {0, 0};
        network.forward(inputs);
        assertEquals(3, network.getLayers().length);
        assertEquals(0.6655119636352238, network.getLayers()[2].getNeurons()[0].getValue(), DELTA);
    }

    @Test
    public void testBackward() { // Run only once !
        double[] inputs = {0, 0};
        double learningRate = 0.5;
        Data tData = new Data(new double[]{1, 1}, new double[]{0});
        network.forward(inputs);
        network.backward(learningRate, tData);
        assertEquals(0.8764582604256632, network.getLayers()[1].getNeurons()[0].getWeights()[0], DELTA);
        assertEquals(-0.6894825281109911, network.getLayers()[1].getNeurons()[0].getWeights()[1], DELTA);
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
        assertEquals(0.29964802390862466, loss, DELTA);
    }
}
