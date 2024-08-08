package container;

import function.Function;

import java.io.*;
import java.util.Objects;

public class Network {
    private static Layer[] layers;
    private static Data[] datasets;

    public Network(int[] nLayers, Function[] functions, Data[] data) {
        // TODO: Fill the constructor of this class
        //
        // END
    }

    public static void forward(double[] inputs) {
        layers[0] = new Layer(inputs);

        for (int i = 1; i < layers.length; i++) {
            for (int j = 0; j < layers[i].getNeurons().length; j++) {
                double sum = layers[i].getNeurons()[j].getBias();
                for (int k = 0; k < layers[i - 1].getNeurons().length; k++) {
                    sum += layers[i - 1].getNeurons()[k].getValue() * layers[i].getNeurons()[j].getWeights()[k];
                }
                double output = layers[i].applyActivation(sum);
                layers[i].getNeurons()[j].setValue(output);
            }
        }
    }

    public static void backward(double learningRate, Data tData) { // This required further knowledge in NN (Out of this class scope)
        int nLayers = layers.length;
        int outIndex = nLayers - 1;

        // Output layer
        for (int i = 0; i < layers[outIndex].getNeurons().length; i++) {
            double output = layers[outIndex].getNeurons()[i].getValue();
            double target = tData.getOutput()[i];
            double derivative = output - target;
            double delta = derivative * layers[outIndex].applyActivationDerivative(output);
            layers[outIndex].getNeurons()[i].setGradient(delta);

            // Update weights and bias
            for (int j = 0; j < layers[outIndex].getNeurons()[i].getWeights().length; j++) {
                double previousOutput = layers[outIndex - 1].getNeurons()[j].getValue();
                double error = delta * previousOutput;
                layers[outIndex].getNeurons()[i].getWeights()[j] -= learningRate * error;
            }
            layers[outIndex].getNeurons()[i].setBias(layers[outIndex].getNeurons()[i].getBias() - learningRate * delta);
        }

        // Hidden layers
        for (int i = outIndex - 1; i > 0; i--) {
            for (int j = 0; j < layers[i].getNeurons().length; j++) {
                double output = layers[i].getNeurons()[j].getValue();
                double gradientSum = sumGradient(j, i + 1);
                double delta = gradientSum * layers[i].applyActivationDerivative(output);
                layers[i].getNeurons()[j].setGradient(delta);

                // Update weights and bias
                for (int k = 0; k < layers[i].getNeurons()[j].getWeights().length; k++) {
                    double previousOutput = layers[i - 1].getNeurons()[k].getValue();
                    double error = delta * previousOutput;
                    layers[i].getNeurons()[j].getWeights()[k] -= learningRate * error;
                }
                layers[i].getNeurons()[j].setBias(layers[i].getNeurons()[j].getBias() - learningRate * delta);
            }
        }
    }

    public static double sumGradient(int nIndex, int lIndex) {
        // TODO: FILL THIS METHOD
        // This method computes the SUM of gradients for a specific weight index across neurons in a given layer of a neural network.
        // Access the layer specified by `lIndex` using the layers array.
        //
        // Iterate over each neuron in the current “lIndex” layer.
        // For each neuron, retrieve the weight at index `nIndex` and multiply it by the gradient of the neuron.
        //
        // Return the sum value
        //
        //
        return 0; // CHANGE THIS !
        // END
    }

    public static void train(int trainingIterations, double learningRate) {
        // TODO: FILL THIS METHOD
        // This method iterates over the training dataset for the specified number of training Iterations.
        // -> Within each iteration:
        //      Initializes double totalLoss variable to accumulate the total loss over all data in the dataset.
        //      loops over each data in the datasets array.
        //      -> In Each loops of the dataset:
        //          Performs a forward method using data value, use getData to access data.
        //          Performs a backward method to update the network's parameters (weights) based on the learning rate and dataset.
        //             Calculate loss using its function by passing variables with Last layers’ neurons (use “getNeurons” from the last layer) and dataset output (use “dataset.getOutput” ) and the result to the variable totalLoss.
        //      Calculates the average loss across all instances (By totalLoss over datasets length)
        //      Print out the iteration number and average loss.
        //
        // END
    }

    public static double calculateLoss(Neuron[] outputNeurons, double[] expectedOutput) {
        // TODO: FILL THIS METHOD
        // Using sqaure error as loss metrics
        // Initialize variables to store the error value.
        // -> Iterate over each output neuron using a for loop.
        //      Get the error as the differences between each the expectedOutput array and each neuron array value.
        //      Add the error square margin to the previous initialized value.
        //
        // Return the average squared error with the total number of output neurons. (Average can be get by multiplying overall error by 0.5)
        //
        return 0; // CHANGE THIS !
        // END
    }

    public static double[] predict(double[] data) {
        // TODO: FILL THIS METHOD
        // Call method forward and give data array as input.
        // Initialize prediction array with double type with matching length of the last Layer of Neuron.
        // -> Then for each index in the prediction array,
        //      assing the value of each Nueron on the last Layer to prediction index value.
        //      (Basically copy the last output of each Neuron value to the double array).
        //
        // Return the array.
        //
        return null; // CHANGE THIS !
        // END
    }

    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(layers.length + "\n");

            for (int i = 0; i < layers.length; i++) {
                Layer layer = layers[i];
                if (i == 0) {  // Input layer
                    writer.write(layer.getNeurons().length + "\n");
                    writer.write("null\n");  // No activation function for input layer
                    for (Neuron neuron : layer.getNeurons()) {
                        writer.write(neuron.getValue() + "\n");
                    }
                } else {  // Hidden and output layers
                    writer.write(layer.getNeurons().length + "\n");
                    writer.write(layer.getFunction() + "\n");
                    for (Neuron neuron : layer.getNeurons()) {
                        writer.write(neuron.getBias() + "\n");
                        for (double weight : neuron.getWeights()) {
                            writer.write(weight + " ");
                        }
                        writer.write("\n");
                    }
                }
            }
            System.out.println("Network saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving network: " + e.getMessage());
        }
    }

    public static void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int layerCount = Integer.parseInt(reader.readLine().trim());
            Layer[] loadedLayers = new Layer[layerCount];

            for (int i = 0; i < layerCount; i++) {
                int neuronCount = Integer.parseInt(Objects.requireNonNull(readNonEmptyLine(reader)));
                String functionStr = readNonEmptyLine(reader);
                assert functionStr != null;
                Function function = (functionStr.equals("null")) ? null : Function.valueOf(functionStr);

                if (i == 0) {  // Input Layer
                    double[] inputs = new double[neuronCount];
                    for (int j = 0; j < neuronCount; j++) {
                        inputs[j] = Double.parseDouble(Objects.requireNonNull(readNonEmptyLine(reader)));
                    }
                    loadedLayers[i] = new Layer(inputs);
                } else {  // Hidden and Output Layers
                    int prevLayerSize = loadedLayers[i-1].getNeurons().length;
                    loadedLayers[i] = new Layer(prevLayerSize, neuronCount, function);
                    Neuron[] neurons = new Neuron[neuronCount];
                    for (int j = 0; j < neuronCount; j++) {
                        double bias = Double.parseDouble(Objects.requireNonNull(readNonEmptyLine(reader)));
                        String[] weightStr = Objects.requireNonNull(readNonEmptyLine(reader)).split(" ");
                        double[] weights = new double[weightStr.length];
                        for (int k = 0; k < weightStr.length; k++) {
                            weights[k] = Double.parseDouble(weightStr[k]);
                        }
                        neurons[j] = new Neuron(weights, bias);
                    }
                    loadedLayers[i].setNeurons(neurons);
                }
            }

            setLayers(loadedLayers);
            System.out.println("Network loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading network: " + e.getMessage());
        }
    }

    private static String readNonEmptyLine(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
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
