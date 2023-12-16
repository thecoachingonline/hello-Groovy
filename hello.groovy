import java.math.BigInteger

class NTRU {

    private static final int N = 4096;
    private static final int q = 2^2048 - 2^1024 - 1;
    private static final int d = 2^128;

    static BigInteger[] generatePrivateKey() {
        BigInteger p = BigInteger.probablePrime(N, true);
        BigInteger g = BigInteger.random(N);
        return new BigInteger[]{p, g};
    }

    static BigInteger encrypt(BigInteger message, BigInteger[] privateKey) {
        BigInteger p = privateKey[0];
        BigInteger g = privateKey[1];
        return g.modPow(message, p);
    }

    static BigInteger decrypt(BigInteger ciphertext, BigInteger[] privateKey) {
        BigInteger p = privateKey[0];
        BigInteger g = privateKey[1];
        return g.modPow(ciphertext, p.modInverse(q));
    }
}

def message = "สวัสดีชาวโลก"
def privateKey = NTRU.generatePrivateKey()
def ciphertext = NTRU.encrypt(message.toBigInteger(), privateKey)
def plaintext = NTRU.decrypt(ciphertext, privateKey)

println "Original message: $message"
println "Ciphertext: $ciphertext"
println "Decrypted message: $plaintext"