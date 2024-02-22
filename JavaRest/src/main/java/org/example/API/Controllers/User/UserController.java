package org.example.API.Controllers.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.example.API.Controllers.Base.BaseController;
import org.example.API.Helpers.HttpQueryHelper;
import org.example.Business.Dtos.Company.AddCompanyDto;
import org.example.Business.Dtos.User.AddUserDto;
import org.example.Business.Dtos.User.UpdateUserDto;
import org.example.Business.Services.User.UserService;
import org.example.Data.Models.Results.Factory.ResultCreater;

import java.io.IOException;
import java.io.OutputStream;

public class UserController extends BaseController {

    private UserService userService;
    private ObjectMapper mapper;
    public UserController(HttpServer server) throws IOException {
        this.server = server;
        this.userService = new UserService();
        this.mapper = new ObjectMapper();
        InitServices();
    }


    public void InitServices() {
        server.createContext("/api/user", (exchange -> {
            switch (exchange.getRequestMethod()) {
                case "GET":
                    GetUser(exchange);
                case "POST":
                    AddUser(exchange);
                case "DELETE":
                    DeleteUser(exchange);
                case "PUT":
                    UpdateUser(exchange);
                default:
                    exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));
    }

    private void GetUser(HttpExchange exchange) throws IOException {
        var query = HttpQueryHelper.getQueries(exchange);
        int value = Integer.parseInt(query.get("id"));
        var result = userService.GetUser(value);
        String responseJson = mapper.writeValueAsString(result);
        exchange.sendResponseHeaders(result.StatusCode, responseJson.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(responseJson.getBytes());
        output.flush();
    }

    private void AddUser(HttpExchange exchange) throws IOException {
        var body = HttpQueryHelper.getBody(exchange);

        try
        {
            var addUserDto = mapper.readValue(body, AddUserDto.class);
            var result = userService.AddUser(addUserDto);
            var resultJson = mapper.writeValueAsString(result);
            exchange.sendResponseHeaders(result.StatusCode, resultJson.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(resultJson.getBytes());
            output.flush();
        }
        catch (Exception ex)
        {
            var result = ResultCreater.CreateExceptionResult(ex);
            var responseJson = mapper.writeValueAsString(result);
            exchange.sendResponseHeaders(result.StatusCode,responseJson.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseJson.getBytes());
            output.flush();
        }
    }

    private void UpdateUser(HttpExchange exchange) throws IOException {
        var body = HttpQueryHelper.getBody(exchange);

        try
        {
            var updateUserDto = mapper.readValue(body, UpdateUserDto.class);
            var result = userService.UpdateUser(updateUserDto);
            var resultJson = mapper.writeValueAsString(result);
            exchange.sendResponseHeaders(result.StatusCode, resultJson.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(resultJson.getBytes());
            output.flush();
        }
        catch (Exception ex)
        {
            var result = ResultCreater.CreateExceptionResult(ex);
            var responseJson = mapper.writeValueAsString(result);
            exchange.sendResponseHeaders(result.StatusCode,responseJson.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseJson.getBytes());
            output.flush();
        }
    }

    private void DeleteUser(HttpExchange exchange) throws IOException {
        var query = HttpQueryHelper.getQueries(exchange);
        int userId = Integer.parseInt(query.get("id"));
        var result = userService.DeleteUser(userId);
        String responseJson = mapper.writeValueAsString(result);
        exchange.sendResponseHeaders(result.StatusCode, responseJson.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(responseJson.getBytes());
        output.flush();
    }
}
