import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var hc = new HImplement();

        System.out.println(Arrays.deepToString(hc.randomKey(3, 100, -3,3)));
    }
}