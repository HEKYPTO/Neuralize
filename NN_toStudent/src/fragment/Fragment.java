package fragment;

import container.Data;
import container.Layer;
import container.Network;
import container.Neuron;

public class Fragment {
//    public static void backward(double learningRate, Data tData) {
//        int nLayers = layers.length;
//        int outIndex = nLayers - 1;
//
//        for (int i = 0; i < layers[outIndex].getNeurons().length; i++) {
//            double output = layers[outIndex].getNeurons()[i].getValue();
//            double target = tData.getOutput()[i];
//            double derivative = output - target;
//            double delta = derivative * layers[outIndex].applyActivationDerivative(output);
//            layers[outIndex].getNeurons()[i].setGradient(delta);
//            for (int j = 0; j < layers[outIndex].getNeurons()[i].getWeights().length; j++) {
//                double previousOutput = layers[outIndex - 1].getNeurons()[j].getValue();
//                double error = delta * previousOutput;
//                layers[outIndex].getNeurons()[i].getCacheWeights()[j] = layers[outIndex].getNeurons()[i].getWeights()[j] - learningRate * error;
//            }
//        }
//
//        for (int i = outIndex - 1; i > 0; i--) {
//            for (int j = 0; j < layers[i].getNeurons().length; j++) {
//                double output = layers[i].getNeurons()[j].getValue();
//                double gradientSum = sumGradient(j, i + 1);
//                double delta = (gradientSum) * layers[i].applyActivationDerivative(output);
//                layers[i].getNeurons()[j].setGradient(delta);
//                for (int k = 0; k < layers[i].getNeurons()[j].getWeights().length; k++) {
//                    double previousOutput = layers[i - 1].getNeurons()[k].getValue();
//                    double error = delta * previousOutput;
//                    layers[i].getNeurons()[j].getCacheWeights()[k] = layers[i].getNeurons()[j].getWeights()[k] - learningRate * error;
//                }
//            }
//        }
//
//        for (Layer layer : layers) {
//            for (Neuron neuron : layer.getNeurons()) {
//                neuron.updateWeights();
//            }
//        }
//    }
}
