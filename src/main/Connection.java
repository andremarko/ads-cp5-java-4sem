package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
// classe ponte entre Server e Client. Lida com o envio e recebimento de mensagens ttanto quanto a chave publica.
public class Connection {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String encryptedMessage) {
        writer.println(encryptedMessage);
        writer.flush();
    }

    public String receive() throws IOException {
        return reader.readLine();
    }

    public void sendPublicKey(String publicKey) {
        writer.println(publicKey);
        writer.flush();
    }

    public String receivePublicKey() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
