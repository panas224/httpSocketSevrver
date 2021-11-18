package com.httpServer;

import com.httpServer.config.ConfigurationManager;
import com.httpServer.config.Configutarion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server has starting....");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configutarion configutarion = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using port :" + configutarion.getPort());
        System.out.println("Using webroot :" + configutarion.getWebRoot());

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(configutarion.getPort());
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            //reading

            String html = "<html><head><title>Http server</title></head><body><h1>Server with socket</h1></body></html>";
            final String CRLF = "\n\r";
            String statusResponse = "HTTP/1.1 200 OK" + CRLF + "Content-Length:" + html.getBytes().length + CRLF + CRLF + html + CRLF + CRLF;
            outputStream.write(statusResponse.getBytes());

            //writing

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
