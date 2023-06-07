import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var hc = new HImplement();

        var key = hc.randomKey(4,10 , -5, 5);
        System.out.println(Arrays.toString(key));
        var enc = hc.encrypt("LINEARALGEBRA", key[0]);
        System.out.println(enc);
        var dec = hc.encrypt(enc, key[1]);
        System.out.println(dec);

    }
}