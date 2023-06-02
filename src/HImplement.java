import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    @Override
    public String encrypt(String s, int[][] key) {

        return null;
    }

    @Override
    public String decrypt(String s, int[][] key) {
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
        int[][] key = new int[n][n];
        int[][] invert = new int[n][n];
        // create identity matrix
        for (int i = 0; i < n; i++) {
            key[i][i] = 1;
            invert[i][i] = 1;
        }
        // perform random gauss jordan operations to create an invertible matrix
        for (int i = 0; i < opn; i++) {
            int r1 = (int) (Math.random() * n);
            int r2 = r1; //prevent r1 = r2
            do {
                r2 = (int) (Math.random() * n);
            } while (r1 == r2);

            switch ((int) (Math.random() * 4)) {
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
                    gJOp.push(new Integer[]{2,r1,r2});
                }
                case 3 -> {
                    // prevent row multiplication by 0
                    int k = 0;
                    do {
                        k = (int) (Math.random() * (ub - lb)) + lb;
                    } while (k == 0);
                    Gauss_Jordan.rowMultiply(key, r1, k);
                    gJOp.push(new Integer[]{3, r1, k});
                }
            }


        }

        // calculate inverse matrix
        for (int i = 0; i < gJOp.size(); i++) {
            var e = gJOp.pop();
            switch (e[0]) {
                case 0 -> Gauss_Jordan.swap(invert,e[1],e[2]);
                case 1 -> Gauss_Jordan.rowAdd(invert,e[1],e[2]);
                case 2 -> Gauss_Jordan.rowSubtract(invert,e[1],e[2]);
                case 3 -> Gauss_Jordan.rowMultiply(invert,e[1],e[2]);
            }
        }
        return null;
    }
}
