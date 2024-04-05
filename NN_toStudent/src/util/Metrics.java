package util;

public class Metrics {
    public static double squaredError(double output, double target) {
        return 0.5 * Math.pow(2, (target - output));
    }

    public static double sumSquaredError(double[] outputs, double[] targets) {
        double sum = 0;
        for (int i = 0; i < outputs.length; i++) {
            sum += squaredError(outputs[i], targets[i]);
        }
        return sum;
    }
}
