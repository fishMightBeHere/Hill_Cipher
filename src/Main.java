import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var hc = new HImplement();

        int[][] key = {{2,0},{0,2}};

        System.out.println(hc.encrypt("abc",key));
    }
}