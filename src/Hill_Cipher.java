public interface Hill_Cipher {
    String encrypt(String s, int[][] key);
    String decrypt(String s, int[][] key);
    String getKey();
    int[][] randomKey(int n, int lb, int ub);
}
