public interface Hill_Cipher {
    String encrypt(String s, String key);
    String decrypt(String s, String key);
    String[] randomKey(int n, int opn, int lb, int ub);
}
