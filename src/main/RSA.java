package main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

// atributos imutáveis
public class RSA {
    private final static BigInteger p = new BigInteger("61");
    private final static BigInteger q = new BigInteger("53");
    // modulus
    private final static BigInteger n = p.multiply(q);
    private final static BigInteger e = new BigInteger("17");
    // função totiente
    private final static BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    private final static BigInteger d = e.modInverse(phi);

    public static String privateKey() {
    return n + "," + d;
    }

    public static String publicKey() {
    return n + "," + e;
}

    // metodo encriptador, recebe publicKey para encriptar a mensagem a ser enviada
     public static String encrypt(String message, String publicKey) throws Exception {
        String[] parts = publicKey.split(",");

        BigInteger nKey = new BigInteger(parts[0]);
        BigInteger eKey = new BigInteger(parts[1]);

        byte[] messageBytes = message.getBytes(StandardCharsets.US_ASCII);
        StringBuilder encryptedStringBuilder = new StringBuilder();
        // para cada bye em messageBytes
        for (byte b : messageBytes) {
            // encripte
            BigInteger messageBigInt = new BigInteger(new byte[]{b});
            BigInteger encrypted = messageBigInt.modPow(eKey, nKey);
            encryptedStringBuilder.append(encrypted).append(" ");
        }
        return encryptedStringBuilder.toString().trim();
    }
    // decifrador, recebe privateKey para decriptar a mensagem recebida
    public static String decrypt(String encryptedMessage, String privateKey) throws Exception {
        String[] parts = privateKey.split(",");

        BigInteger nKey = new BigInteger(parts[0]);
        BigInteger dKey = new BigInteger(parts[1]);

        String[] encryptedParts = encryptedMessage.split(" ");
        StringBuilder decryptedStringBuilder = new StringBuilder();
        for (String part : encryptedParts) {
            BigInteger encryptedBigInt = new BigInteger(part);
            BigInteger decrypted = encryptedBigInt.modPow(dKey, nKey);
            decryptedStringBuilder.append((char) decrypted.byteValueExact());
        }
        return decryptedStringBuilder.toString();
    }
}
