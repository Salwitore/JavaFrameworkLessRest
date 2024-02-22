package org.example.API;

import com.sun.net.httpserver.HttpServer;
import org.example.API.Helpers.InitilaizeControllerHelper;
import org.example.API.Helpers.ServerProvider;
import org.example.Utils.Hibernate.HibernateUtils;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        HttpServer server = new ServerProvider(8082).server;
        HibernateUtils.BuildSessionFactory();
        new InitilaizeControllerHelper(server).InitilaizeController();

    }
}