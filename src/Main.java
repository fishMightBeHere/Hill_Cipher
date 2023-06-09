import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var hc = new HImplement();

        var key = hc.randomKey(4,100 , -10, 10);
        System.out.println(Arrays.toString(key));
        var enc = hc.encrypt("PRINCETONHIGHSCHOOL", key[0]);
        System.out.println(enc);
        var dec = hc.encrypt(enc, key[1]);
        System.out.println(dec);

    }
}