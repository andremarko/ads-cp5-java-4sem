package main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

// classe privada
// atributos imutáveis
public class RSA {
    // números primos
    BigInteger p = new BigInteger("17");
    BigInteger q = new BigInteger("23");
    BigInteger n = p.multiply(q);
    BigInteger e = new BigInteger("3");
    BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    BigInteger d = e.modInverse(phi);

    public String privateKey() {
        return n + "," + d;
    }

    public String publicKey() {
        return n + "," + e;
    }



// metodo encriptador, recebe publicKey para encriptar a mensagem a ser enviada
public String encrypt(String message, String publicKey) throws Exception {
    byte[] messageBytes = message.getBytes(StandardCharsets.US_ASCII);
    StringBuilder encryptedStringBuilder = new StringBuilder();
    // para cada bye em messageBytes
    for (byte b : messageBytes) {
        // encripte
        BigInteger messageBigInt = new BigInteger(new byte[] { b });
        BigInteger encrypted = messageBigInt.modPow(e, n);
    }
    return encryptedStringBuilder.toString().trim();
}
// decifrador
public String decrypt(String encryptedMessage, String privateKey) throws Exception {
    String[] encryptedParts = encryptedMessage.split(" ");
    StringBuilder decryptedStringBuilder = new StringBuilder();
    for (String part : encryptedParts) {
        BigInteger encryptedBigInt = new BigInteger(part);
        BigInteger decrypted = encryptedBigInt.modPow(d, n);
        decryptedStringBuilder.append((char) decrypted.byteValueExact());
    }
    return decryptedStringBuilder.toString();
}
}
