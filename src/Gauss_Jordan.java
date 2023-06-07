import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gauss_Jordan {

    /**
     * swap two rows
     * @param matrix input matrix
     * @param r1 row 1
     * @param r2 row 2
     * @return swapped matrix
     */
    public static double[][] swap(double[][] matrix, int r1, int r2) {
        List<Double> v = new ArrayList<>();
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
    public static double[][] rowAdd(double[][] matrix, int r1, int r2) {
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
    public static double[][] rowSubtract(double[][] matrix, int r1, int r2) {
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
    public static double[][] rowMultiply(double[][] matrix, int r, double k) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[r][i] *= k;
        }
        return matrix;
    }

    /**
     * Unit Testing
     *
     * @param args
     */
    public static void main(String[] args) {
        double[][] matrix1 = {{1,2},{3,4}};
        swap(matrix1,0,1);
        System.out.println(Arrays.deepToString(matrix1));
        rowAdd(matrix1,0,1);
        System.out.println(Arrays.deepToString(matrix1));
        rowSubtract(matrix1,0,1);
        System.out.println(Arrays.deepToString(matrix1));
        swap(matrix1,0,1);
        System.out.println(Arrays.deepToString(matrix1));

        rowMultiply(matrix1,1,2);
        System.out.println(Arrays.deepToString(matrix1));
        rowMultiply(matrix1,1,-1);
        System.out.println(Arrays.deepToString(matrix1));
        rowMultiply(matrix1,1,-0.5);
        System.out.println(Arrays.deepToString(matrix1));
        rowMultiply(matrix1,1,2);
    }
}
