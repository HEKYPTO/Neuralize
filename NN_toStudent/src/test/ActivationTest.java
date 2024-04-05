package test;

import org.junit.Before;
import org.junit.Test;

import util.Activation;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ActivationTest {

    ArrayList<Double> v1 = new ArrayList<>();
    ArrayList<ArrayList<Double>> v2 = new ArrayList<>();

    @Before
    public void setup() {
        final long SEED = 42;
        Random random = new Random(SEED);

        for (int i = 0; i < 3; i++) {
            v1.add(random.nextDouble() * 2 - 1);
        }

        for (int i = 0; i < 2; i++) {
            ArrayList<Double> arr = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                arr.add(random.nextDouble() * 2 - 1);
            }
            v2.add(arr);
        }
    }

    @Test
    public void sigmoidTest() {
        v1.forEach(e -> {
            int idx = v1.indexOf(e);
            v1.set(idx, Activation.sigmoid(e));
        });

        assertEquals(0.6118576159038136, v1.get(0), 0.0001);
        assertEquals(0.5906001565448589, v1.get(1), 0.0001);
        assertEquals(0.40550934226825636, v1.get(2), 0.0001);

        v2.forEach(row -> row.forEach(el -> {
            int idx = row.indexOf(el);
            row.set(idx, Activation.sigmoid(el));
        }));

        assertEquals(0.39034957868302095, v2.get(0).get(0), 0.0001);
        assertEquals(0.5820264896935841, v2.get(0).get(1), 0.0001);
        assertEquals(0.6914153497225994, v2.get(0).get(2), 0.0001);
        assertEquals(0.43476542893979586, v2.get(1).get(0), 0.0001);
        assertEquals(0.38971654530194333, v2.get(1).get(1), 0.0001);
        assertEquals(0.4818347863280159, v2.get(1).get(2), 0.0001);
    }

    @Test
    public void sigmoidDerivativeTest() {
        v1.forEach(e -> {
            int idx = v1.indexOf(e);
            v1.set(idx, Activation.sigmoidDerivative(e));
        });

        assertEquals(0.2374878737643149, v1.get(0), 0.0001);
        assertEquals(0.24179161163404705, v1.get(1), 0.0001);
        assertEquals(0.2410715156014225, v1.get(2), 0.0001);

        v2.forEach(row -> row.forEach(el -> {
            int idx = row.indexOf(el);
            row.set(idx, Activation.sigmoidDerivative(el));
        }));

        assertEquals(0.237976785105009, v2.get(0).get(0), 0.0001);
        assertEquals(0.24327165498854836, v2.get(0).get(1), 0.0001);
        assertEquals(0.21336016389057494, v2.get(0).get(2), 0.0001);
        assertEquals(0.24574445073859116, v2.get(1).get(0), 0.0001);
        assertEquals(0.2378375596198617, v2.get(1).get(1), 0.0001);
        assertEquals(0.24967002501225116, v2.get(1).get(2), 0.0001);
    }

    @Test
    public void tanhTest() {
        v1.forEach(e -> {
            int idx = v1.indexOf(e);
            v1.set(idx, Activation.tanh(e));
        });
        assertEquals(0.42610456708345396, v1.get(0), 0.0001);
        assertEquals(0.35087998929164627, v1.get(1), 0.0001);
        assertEquals(-0.3649295594156838, v1.get(2), 0.0001);

        v2.forEach(row -> row.forEach(el -> {
            int idx = row.indexOf(el);
            row.set(idx, Activation.tanh(el));
        }));
        assertEquals(-0.41847597878273035, v2.get(0).get(0), 0.0001);
        assertEquals(0.31950694688553094, v2.get(0).get(1), 0.0001);
        assertEquals(0.6677904659753099, v2.get(0).get(2), 0.0001);
        assertEquals(-0.25657088409556894, v2.get(1).get(0), 0.0001);
        assertEquals(-0.4206684013855856, v2.get(1).get(1), 0.0001);
        assertEquals(-0.07256507604761733, v2.get(1).get(2), 0.0001);
    }

    @Test
    public void tanhDerivativeTest() {
        v1.forEach(e -> {
            int idx = v1.indexOf(e);
            v1.set(idx, Activation.tanhDerivative(e));
        });
        assertEquals(0.8184348979106223, v1.get(0), 0.0001);
        assertEquals(0.8768832331146942, v1.get(1), 0.0001);
        assertEquals(0.8668264166646749, v1.get(2), 0.0001);

        v2.forEach(row -> row.forEach(el -> {
            int idx = row.indexOf(el);
            row.set(idx, Activation.tanhDerivative(el));
        }));

        assertEquals(0.8248778551818359, v2.get(0).get(0), 1e-08);
        assertEquals(0.8979153108918865, v2.get(0).get(1), 1e-08);
        assertEquals(0.5540558935524784, v2.get(0).get(2), 1e-08);
        assertEquals(0.9341713814344181, v2.get(1).get(0), 1e-08);
        assertEquals(0.8230380960756959, v2.get(1).get(1), 1e-08);
        assertEquals(0.9947343097382035, v2.get(1).get(2), 1e-08);
    }

    @Test
    public void reluTest() {
        v1.forEach(e -> {
            int idx = v1.indexOf(e);
            v1.set(idx, Activation.relu(e));
        });
        assertEquals(0.4551273600657362, v1.get(0), 0.0001);
        assertEquals(0.36644694351969087, v1.get(1), 0.0001);
        assertEquals(0.0, v1.get(2), 0.0001);

        v2.forEach(row -> row.forEach(el -> {
            int idx = row.indexOf(el);
            row.set(idx, Activation.relu(el));
        }));
        assertEquals(0.0, v2.get(0).get(0), 0.0001);
        assertEquals(0.33109790358914726, v2.get(0).get(1), 0.0001);
        assertEquals(0.8067445293443565, v2.get(0).get(2), 0.0001);
        assertEquals(0.0, v2.get(1).get(0), 0.0001);
        assertEquals(0.0, v2.get(1).get(1), 0.0001);
        assertEquals(0.0, v2.get(1).get(2), 0.0001);
    }

    @Test
    public void reluDerivativeTest() {
        v1.forEach(e -> {
            int idx = v1.indexOf(e);
            v1.set(idx, Activation.reluDerivative(e));
        });
        assertEquals(1, v1.get(0), 0.0001);
        assertEquals(1, v1.get(1), 0.0001);
        assertEquals(0, v1.get(2), 0.0001);

        v2.forEach(row -> row.forEach(el -> {
            int idx = row.indexOf(el);
            row.set(idx, Activation.reluDerivative(el));
        }));
        assertEquals(0, v2.get(0).get(0), 0.0001);
        assertEquals(1, v2.get(0).get(1), 0.0001);
        assertEquals(1, v2.get(0).get(2), 0.0001);
        assertEquals(0, v2.get(1).get(0), 0.0001);
        assertEquals(0, v2.get(1).get(1), 0.0001);
        assertEquals(0, v2.get(1).get(2), 0.0001);
    }
}
