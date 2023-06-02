public interface Hill_Cipher {
    String encrypt(String s, int[][] key);
    String decrypt(String s, int[][] key);
    String[] randomKey(int n, int opn, int lb, int ub);
}
