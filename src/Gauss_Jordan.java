import java.util.ArrayList;
import java.util.List;

public class Gauss_Jordan {

    /**
     * swap two rows
     * @param matrix input matrix
     * @param r1 row 1
     * @param r2 row 2
     * @return swapped matrix
     */
    public static int[][] swap(int[][] matrix, int r1, int r2) {
        List<Integer> v = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            v.add(matrix[r1][i]);
            matrix[r1][i] = matrix[r2][i];
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[r2][i] = v.get(i);
        }
        return matrix;
    }

    /**
     * r1 = r1 + r2
     * @param matrix input matrix
     * @param r1 row 1
     * @param r2 row 2
     * @return matrix with r1 = r1 + r2
     */
    public static int[][] rowAdd(int[][] matrix, int r1, int r2) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[r1][i] += matrix[r2][i];
        }
        return matrix;
    }

    /**
     *  r1 = r1 - r2
     * @param matrix input matrix
     * @param r1 row 1
     * @param r2 row 2
     * @return matrix with r1 = r1 - r2
     */
    public static int[][] rowSubtract(int[][] matrix, int r1, int r2) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[r1][i] -= matrix[r2][i];
        }
        return matrix;
    }

    /**
     * r = r*k
     * @param matrix input matrix
     * @param r row
     * @param k value
     * @return matrix with r = r*k
     */
    public static int[][] rowMultiply(int[][] matrix, int r, int k) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[r][i] *= k;
        }
        return matrix;
    }

}
