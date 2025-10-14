package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    RSA rsa = new RSA();
    ServerSocket serverSocket;

    public void startServer() throws IOException, Exception {
        serverSocket = new ServerSocket(9600);
        System.out.println("Server started, Waiting for client connection...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        // cria conexão
        Connection conn = new Connection(clientSocket);

        // envia chave publica para o client
        String serverPublicKey = rsa.publicKey();
        conn.sendPublicKey(serverPublicKey);
        System.out.println("Server Public Key sended");

        // recebe chave publica do client
        String clientPublicKey = conn.receivePublicKey();
        System.out.println("Client Public Key received: " + clientPublicKey);

        // decripta com chave privada
        String receivedMessage = conn.receive();
        System.out.println("Message received: " + receivedMessage);
        String decryptedMessage = rsa.decrypt(receivedMessage, rsa.privateKey());
        System.out.println("Client sended: " + decryptedMessage);

        // mensagem de resposta para o client
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write your message to Client: ");
        String response = scanner.nextLine();

        // mensagem encriptada para o client
        String encryptedResponse = rsa.encrypt(response, clientPublicKey);
        System.out.println("Server sended: " + encryptedResponse);
        conn.send(encryptedResponse);

        System.out.println("Response sent");
        // fecha conexão
        conn.close();
        System.out.println("Connection closed");

    }
    public static void main(String[] args) throws IOException, Exception {
        Server server = new Server();
        server.startServer();
    }
}
