package container;

import function.Function;

import static util.Metrics.squaredError;

public class Network {
    private static Layer[] layers;
    private static Data[] datasets;

    public Network(int[] nLayers, Function[] functions, Data[] data) {
        Neuron.setRangeWeight(-1, 1);

        layers = new Layer[nLayers.length];
        for (int i = 0; i < nLayers.length; i++) {
            if (i == 0) layers[i] = null;
            else layers[i] = new Layer(nLayers[i - 1], nLayers[i], functions[i - 1]);
        }

        datasets = new Data[data.length];
        for (int i = 0; i < data.length; i++) {
            datasets[i] = new Data(data[i].getData(), data[i].getOutput());
        }
    }

    public static void forward(double[] inputs) {
        layers[0] = new Layer(inputs);

        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getNeurons().length; j++) {
                double sum = 0;
                for (int k = 0; k < layers[i - 1].getNeurons().length; k++) {
                    sum += layers[i - 1].getNeurons()[k].getValue() * layers[i].getNeurons()[j].getWeights()[k];
                }
                double output = layers[i].applyActivation(sum);
                layers[i].getNeurons()[j].setValue(output);
            }
        }
    }

    public static void backward(double learningRate, Data tData) {
        int nLayers = layers.length;
        int outIndex = nLayers - 1;

        for (int i = 0; i < layers[outIndex].getNeurons().length; i++) {
            double output = layers[outIndex].getNeurons()[i].getValue();
            double target = tData.getOutput()[i];
            double derivative = output - target;
            double delta = derivative * layers[outIndex].applyActivationDerivative(output);
            layers[outIndex].getNeurons()[i].setGradient(delta);
            for (int j = 0; j < layers[outIndex].getNeurons()[i].getWeights().length; j++) {
                double previousOutput = layers[outIndex - 1].getNeurons()[j].getValue();
                double error = delta * previousOutput;
                layers[outIndex].getNeurons()[i].getCacheWeights()[j] = layers[outIndex].getNeurons()[i].getWeights()[j] - learningRate * error;
            }
        }

        for (int i = outIndex - 1; i > 0; i--) {
            for (int j = 0; j < layers[i].getNeurons().length; j++) {
                double output = layers[i].getNeurons()[j].getValue();
                double gradientSum = sumGradient(j, i + 1);
                double delta = (gradientSum) * layers[i].applyActivationDerivative(output);
                layers[i].getNeurons()[j].setGradient(delta);
                for (int k = 0; k < layers[i].getNeurons()[j].getWeights().length; k++) {
                    double previousOutput = layers[i - 1].getNeurons()[k].getValue();
                    double error = delta * previousOutput;
                    layers[i].getNeurons()[j].getCacheWeights()[k] = layers[i].getNeurons()[j].getWeights()[k] - learningRate * error;
                }
            }
        }

        for (Layer layer : layers) {
            for (Neuron neuron : layer.getNeurons()) {
                neuron.updateWeights();
            }
        }
    }

    public static double sumGradient(int nIndex, int lIndex) {
        double gradientSum = 0;
        Layer currentLayer = layers[lIndex];
        for (Neuron neuron : currentLayer.getNeurons()) {
            gradientSum += neuron.getWeights()[nIndex] * neuron.getGradient();
        }
        return gradientSum;
    }

    public static void train(int trainingIterations, double learningRate) {
        for(int i = 0; i < trainingIterations; i++) {

            double totalLoss = 0;

            for (Data dataset : datasets) {
                forward(dataset.getData());

                double instanceLoss = calculateLoss(layers[layers.length - 1].getNeurons(), dataset.getOutput());
                totalLoss += instanceLoss;

                backward(learningRate, dataset);
            }

            double averageLoss = totalLoss / datasets.length;
            System.out.println("Iteration " + (i + 1) + ", Average Loss: " + averageLoss);
        }
    }

    public static double calculateLoss(Neuron[] outputNeurons, double[] expectedOutput) {
        double squaredErrorSum = 0.0;
        for (int i = 0; i < outputNeurons.length; i++) {
            double output = outputNeurons[i].getValue();
            squaredErrorSum += squaredError(output, expectedOutput[i]);
        }
        return squaredErrorSum / outputNeurons.length;
    }

    public void displayOutputLayer() {
        System.out.println("Output functions:");
        for (Data dataset : datasets) {
            forward(dataset.getData());
            for (Neuron neuron: layers[layers.length - 1].getNeurons()) {
                System.out.println(neuron.getValue());
            }
        }
    }

    public static Layer[] getLayers() {
        return layers;
    }

    public static void setLayers(Layer[] layers) {
        Network.layers = layers;
    }

    public static Data[] getDatasets() {
        return datasets;
    }

    public static void setDatasets(Data[] datasets) {
        Network.datasets = datasets;
    }
}
