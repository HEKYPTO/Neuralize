package test;

import org.junit.Test;

import container.Data;

import static org.junit.Assert.*;

public class DataTest {

    @Test
    public void testConstructorAndGetters() {
        double[] inputData = {1.0, 2.0, 3.0};
        double[] outputData = {4.0, 5.0, 6.0};
        Data dataset = new Data(inputData, outputData);

        assertNotNull(dataset);
        assertArrayEquals(inputData, dataset.getData(), 0.0);
        assertArrayEquals(outputData, dataset.getOutput(), 0.0);
    }

    @Test
    public void testSetData() {
        double[] newData = {7.0, 8.0, 9.0};
        double[] outputData = {4.0, 5.0, 6.0};
        Data dataset = new Data(new double[0], outputData);
        dataset.setData(newData);
        assertArrayEquals(newData, dataset.getData(), 0.0);
    }

    @Test
    public void testSetOutput() {
        double[] inputData = {1.0, 2.0, 3.0};
        double[] newOutput = {10.0, 11.0, 12.0};
        Data dataset = new Data(inputData, new double[0]);
        dataset.setOutput(newOutput);
        assertArrayEquals(newOutput, dataset.getOutput(), 0.0);
    }

    @Test
    public void testSetDataWithNull() {
        double[] outputData = {4.0, 5.0, 6.0};
        Data dataset = new Data(new double[]{1.0, 2.0, 3.0}, outputData);
        dataset.setData(null);
        assertNull(dataset.getData());
    }

    @Test
    public void testSetOutputWithNull() {
        double[] inputData = {1.0, 2.0, 3.0};
        Data dataset = new Data(inputData, new double[]{4.0, 5.0, 6.0});
        dataset.setOutput(null);
        assertNull(dataset.getOutput());
    }
}

