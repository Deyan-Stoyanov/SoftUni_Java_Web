package Handmade_Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String HTML_FILE_PATH = "D:\\Програмиране\\СофтУни\\Java Web\\SoftUni_Java_Web\\Java Web Development Basics\\2.1 HTTP Protocol\\HttpProtocolLab\\src\\Handmade_Server\\index.html";
    private int port;
    private ServerSocket listener;

    public Server(int port) {
        this.port = port;
    }

    private String getResponse(String html) {
        return
                "HTTP/1.1 200 OK" + System.lineSeparator()
                        + "Host: Test Handmade_Server.Server" + System.lineSeparator()
                        + "Content Type: text/html" + System.lineSeparator()
                        + System.lineSeparator()
                        + html;
    }

    public void run() throws IOException {
        this.listener = new ServerSocket(this.port);
        System.out.println("Listening on: http://localhost:" + this.port);
        while (true) {
            Socket clientConnection = this.listener.accept();
            System.out.println("Client Connected!");
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            String line;
            StringBuilder requestContent = new StringBuilder();
            while ((line = reader.readLine()) != null && line.length() > 0) {
                requestContent.append(line).append(System.lineSeparator());
            }
            System.out.println(requestContent.toString());
            String html = getFileContent(HTML_FILE_PATH);
            clientConnection.getOutputStream().write(this.getResponse(html).getBytes());
            reader.close();
            clientConnection.getInputStream().close();
            clientConnection.close();
        }
    }

    private String getFileContent(String path) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String line = fileReader.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = fileReader.readLine();
        }
        return sb.toString();
    }
}
