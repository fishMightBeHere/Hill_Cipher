public class HImplement implements Hill_Cipher{
    /**
     * cornverts a string into a vector representation
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

    @Override
    public String getKey() {
        return null;
    }

    /**
     * generate a random nxn matrix with each element within a lower and upper bound
     * @param n dimension of matrix
     * @param lb lower bound of random elements
     * @param ub upper bound of random elements
     * @return 2d nxn array
     */
    @Override
    public int[][] randomKey(int n, int lb, int ub) {
        int[][] key = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                key[i][j] = (int)(Math.random() * (ub-lb)) + lb;
            }
        }
        return key;
    }
}
