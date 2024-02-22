package org.example.API.Helpers;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServerProvider {
    public HttpServer server;
    private int port = 8080;

    public ServerProvider(int port) throws IOException {
        this.port = port;
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
    }
}
