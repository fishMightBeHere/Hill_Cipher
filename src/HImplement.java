import java.util.*;
import java.util.stream.Collectors;

public class HImplement implements Hill_Cipher {
    /**
     * cornverts a string into a vector representation
     *
     * @param s input string
     * @return array
     */
    private int[] stringToVector(String s) {
        return s.chars().toArray();
    }

    private String vectorToString(int[] v) {
        return Arrays.stream(v).mapToObj(i -> String.valueOf((char) ((i%26)+65))).collect(Collectors.joining());
    }

    private int[] Ax(double[][] matrix, int[] vector) {
        /*
        1 2 | 2
        3 4 | 3
         */
        var output = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            output[i] = sum%26+65;
        }
        return output;
    }

    private double[][] parseKey(String s) {
        Queue<Double> chq = Arrays.stream(s.split(" ")).map(Double::parseDouble).collect(Collectors.toCollection(LinkedList::new));

        assert chq.size() > 0 : "key is empty";
        double[][] key = new double[(int) Math.floor(chq.peek())][(int) Math.floor(chq.remove())];

        var n = Math.sqrt(chq.size());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                assert chq.peek() != null : "missing elements in key";
                key[i][j] = chq.remove();
            }
        }

        return key;
    }

    @Override
    public String encrypt(String s, String k) {

        var key = parseKey(k);

        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() % key.length > 0) {
            sBuilder.append("X");
        }
        s = sBuilder.toString();

        List<int[]> blocks = Arrays.stream(s.split("(?<=\\G.{" + key.length + "})")).map(this::stringToVector).collect(Collectors.toList());

        return blocks.stream().map(i -> Ax(key, i)).map(this::vectorToString).collect(Collectors.joining());
    }

    @Override
    public String decrypt(String s, String key) {
        return null;
    }

    /**
     * generate a random nxn matrix with each element within a lower and upper bound
     *
     * @param n   dimension of matrix
     * @param opn number of gauss jordan operations to perform
     * @param lb  lower bound for multiplication
     * @param ub  upper bound for multiplication
     * @return tuple of key and inverse {key,invert} in a string representation
     */
    @Override
    public String[] randomKey(int n, int opn, int lb, int ub) {
        Stack<Integer[]> gJOp = new Stack<>();
        double[][] key = new double[n][n];
        double[][] invert = new double[n][n];
        // create identity matrix
        for (int i = 0; i < n; i++) {
            key[i][i] = 1;
            invert[i][i] = 1;
        }
        // perform random gauss jordan operations to create an invertible matrix
        for (int i = 0; i < opn; i++) {
            int r1 = (int) (Math.random() * n);
            int r2; //prevent r1 = r2
            do {
                r2 = (int) (Math.random() * n);
            } while (r1 == r2);

            switch ((int) (Math.random() * 3)) {
                case 0 -> {
                    Gauss_Jordan.swap(key, r1, r2);
                    gJOp.push(new Integer[]{0, r1, r2});
                }
                case 1 -> {
                    Gauss_Jordan.rowAdd(key, r1, r2);
                    gJOp.push(new Integer[]{1, r1, r2});
                }
                case 2 -> {
                    Gauss_Jordan.rowSubtract(key, r1, r2);
                    gJOp.push(new Integer[]{2, r1, r2});
                }
                case 3 -> {
                    // prevent row multiplication by 0
                    int k;
                    do {
                        k = (int) (Math.random() * (ub - lb)) + lb;
                    } while (k == 0);
                    Gauss_Jordan.rowMultiply(key, r1, k);
                    gJOp.push(new Integer[]{3, r1, k});
                }
            }
        }

        // calculate inverse matrix
        while (gJOp.size()>0) {
            var e = gJOp.pop();
            switch (e[0]) {
                case 0 -> Gauss_Jordan.swap(invert, e[1], e[2]);
                case 1 -> Gauss_Jordan.rowSubtract(invert, e[1], e[2]);
                case 2 -> Gauss_Jordan.rowAdd(invert, e[1], e[2]);
                case 3 -> Gauss_Jordan.rowMultiply(invert, e[1], 1.0 / e[2]);
            }
        }

        //convert matrix into a string
        StringBuilder k = new StringBuilder();
        k.append(n).append(" ");
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                k.append(key[i][j]).append(" ");
            }
        }

        StringBuilder iv = new StringBuilder();
        iv.append(n).append(" ");
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                iv.append(invert[i][j]).append(" ");
            }
        }

        return new String[]{k.toString(), iv.toString()};
    }
}
