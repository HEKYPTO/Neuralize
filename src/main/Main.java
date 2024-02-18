package main;

import container.Data;
import container.Network;
import function.Function;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[] layerSizes = {2, 6, 1};
        Function[] activationFunctions = {Function.SIGMOID, Function.SIGMOID};

        double[] input1 = new double[] {0, 0};
        double[] input2 = new double[] {0, 1};
        double[] input3 = new double[] {1, 0};
        double[] input4 = new double[] {1, 1};

        double[] out1 = new double[] {0};
        double[] out2 = new double[] {1};
        double[] out3 = new double[] {1};
        double[] out4 = new double[] {0};

        Data[] trainingData = new Data[]{
            new Data(input1, out1),
            new Data(input2, out2),
            new Data(input3, out3),
            new Data(input4, out4),
        };

        Network network = new Network(layerSizes, activationFunctions, trainingData);

        network.displayOutputLayer();

        Scanner sc = new Scanner(System.in);
        System.out.println();
//        System.out.println("Start Training ? : ");
//        String _k = sc.nextLine();

        Network.train(1_000_000, 0.05);

        network.displayOutputLayer();
    }
}
