package org.example.API.Controllers.Base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class BaseController {

    public HttpServer server;

    public BaseController() throws IOException {

    }

    public abstract void InitServices();
}
