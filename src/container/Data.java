package container;

public class Data {

    private double[] data;
    private double[] output;

    public Data(double[] data, double[] output) {
        this.data = data;
        this.output = output;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public double[] getOutput() {
        return output;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }
}
