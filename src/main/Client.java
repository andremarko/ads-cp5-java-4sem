package main;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    RSA rsa = new RSA();
    public void communicateWithServer() throws Exception {
        socket = new Socket("localhost", 9600);
        Connection conn = new Connection(socket);

        // recebe chave publica do servidor
        String serverPublicKey = conn.receivePublicKey();
        System.out.println("Public Key: " + serverPublicKey);

        // envia chave public do client
        String clientPublicKey = rsa.publicKey();
        conn.sendPublicKey(clientPublicKey);

        // lê mensagem
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write your message: ");
        String message = scanner.nextLine();

        String encryptedMessage = rsa.encrypt(message, serverPublicKey);
        System.out.println("Client sended: " + encryptedMessage);
        conn.send(encryptedMessage);

        String receivedMessage = conn.receive();

        String decryptedMessage = rsa.decrypt(receivedMessage, rsa.privateKey());
        System.out.println("Server responded: " + decryptedMessage);

        conn.close();
    }
public static void main(String[] args) {
    try {
        new Client().communicateWithServer();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
