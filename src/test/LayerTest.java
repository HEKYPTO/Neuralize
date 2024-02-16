package test;

import container.Layer;
import function.Function;
import org.junit.Test;
import util.Activation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LayerTest {

    @Test
    public void testConstructorWithFunction() {
        Function function = Function.SIGMOID;
        int inNeurons = 2;
        int nNeurons = 3;
        Layer layer = new Layer(inNeurons, nNeurons, function);
        assertEquals(nNeurons, layer.getNeurons().length);
        assertEquals(function, layer.getFunction());
    }

    @Test
    public void testConstructorWithInput() {
        double[] input = {0.5, -0.3, 0.1};
        Layer layer = new Layer(input);
        assertEquals(input.length, layer.getNeurons().length);
        assertNull(layer.getFunction());
    }

    @Test
    public void testApplyActivationSigmoid() {
        double x = 0.5;
        Layer layer = new Layer(1, 1, Function.SIGMOID);
        assertEquals(Activation.sigmoid(x), layer.applyActivation(x), 0.0001);
    }

    @Test
    public void testApplyActivationDerivativeSigmoid() {
        double x = 0.5;
        Layer layer = new Layer(1, 1, Function.SIGMOID);
        assertEquals(Activation.sigmoidDerivative(x), layer.applyActivationDerivative(x), 0.0001);
    }

    @Test
    public void testApplyActivationTanh() {
        double x = 0.5;
        Layer layer = new Layer(1, 1, Function.TANH);
        assertEquals(Activation.tanh(x), layer.applyActivation(x), 0.0001);
    }

    @Test
    public void testApplyActivationDerivativeTanh() {
        double x = 0.5;
        Layer layer = new Layer(1, 1, Function.TANH);
        assertEquals(Activation.tanhDerivative(x), layer.applyActivationDerivative(x), 0.0001);
    }

    @Test
    public void testApplyActivationRelu() {
        double x = 0.5;
        Layer layer = new Layer(1, 1, Function.RELU);
        assertEquals(Activation.relu(x), layer.applyActivation(x), 0.0001);
    }

    @Test
    public void testApplyActivationDerivativeRelu() {
        double x = 0.5;
        Layer layer = new Layer(1, 1, Function.RELU);
        assertEquals(Activation.reluDerivative(x), layer.applyActivationDerivative(x), 0.0001);
    }
}
