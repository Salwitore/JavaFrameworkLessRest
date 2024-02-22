package org.example.API.Helpers;

import com.sun.net.httpserver.HttpServer;
import org.example.API.Controllers.Company.CompanyController;
import org.example.API.Controllers.User.UserController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class InitilaizeControllerHelper {

    public HttpServer server;
    public InitilaizeControllerHelper(HttpServer server) throws IOException {
        this.server = server;
    }

    public void InitilaizeController() throws IOException {
        new UserController(server);
        new CompanyController(server);
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
