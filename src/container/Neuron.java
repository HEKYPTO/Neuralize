package container;

public class Neuron {

    private static double minWeightValue;
    private static double maxWeightValue;

    private double[] weights;
    private double[] cacheWeights;
    private double gradient;
    private double bias;
    private double value = 0;

    public Neuron(double[] weights, double bias){
        this.weights = weights;
        this.bias = bias;
        this.cacheWeights = this.weights;
        this.gradient = 0;
    }

    public Neuron(double value){
        this.weights = null;
        this.bias = -1;
        this.cacheWeights = null;
        this.gradient = -1;
        this.value = value;
    }

    public static void setRangeWeight(double min, double max) {
        minWeightValue = min;
        maxWeightValue = max;
    }

    public void updateWeights() {
        this.weights = this.cacheWeights;
    }

    public static double getMinWeightValue() {
        return minWeightValue;
    }

    public static void setMinWeightValue(double minWeightValue) {
        Neuron.minWeightValue = minWeightValue;
    }

    public static double getMaxWeightValue() {
        return maxWeightValue;
    }

    public static void setMaxWeightValue(double maxWeightValue) {
        Neuron.maxWeightValue = maxWeightValue;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double[] getCacheWeights() {
        return cacheWeights;
    }

    public void setCacheWeights(double[] cacheWeights) {
        this.cacheWeights = cacheWeights;
    }

    public double getGradient() {
        return gradient;
    }

    public void setGradient(double gradient) {
        this.gradient = gradient;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
