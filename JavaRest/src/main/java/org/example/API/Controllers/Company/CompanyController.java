package org.example.API.Controllers.Company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.example.API.Controllers.Base.BaseController;
import org.example.API.Helpers.HttpQueryHelper;
import org.example.Business.Dtos.Company.AddCompanyDto;
import org.example.Business.Dtos.Company.UpdateCompanyDto;
import org.example.Business.Services.Company.CompanyService;
import org.example.Data.Models.Company.CompanyModel;
import org.example.Data.Models.Results.Factory.ResultCreater;
import org.example.Data.Models.Results.Result;

import java.io.IOException;
import java.io.OutputStream;

public class CompanyController extends BaseController {

    private CompanyService companyService;
    private ObjectMapper mapper;
    public CompanyController(HttpServer server) throws IOException {
        this.companyService = new CompanyService();
        this.server = server;
        this.mapper = new ObjectMapper();
        InitServices();
    }

    @Override
    public void InitServices() {
        server.createContext("/api/company", (exchange -> {
            switch (exchange.getRequestMethod()) {
                case "GET":
                    GetCompany(exchange);
                case "POST":
                    AddCompany(exchange);
                case "DELETE":
                    DeleteCompany(exchange);
                case "PUT":
                    UpdateCompany(exchange);
                default:
                    exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));
    }

    private void GetCompany(HttpExchange exchange) throws IOException
    {
        var query = HttpQueryHelper.getQueries(exchange);
        int value = Integer.parseInt(query.get("id"));
        var result = companyService.GetCompany(value);
        String responseJson = mapper.writeValueAsString(result);
        exchange.sendResponseHeaders(result.StatusCode, responseJson.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(responseJson.getBytes());
        output.flush();
    }

    private void AddCompany(HttpExchange exchange) throws IOException {

        var body = HttpQueryHelper.getBody(exchange);

        try
        {
            var addCompanyDto = mapper.readValue(body, AddCompanyDto.class);
            var result = companyService.AddCompany(addCompanyDto);
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

    private void UpdateCompany(HttpExchange exchange) throws IOException {

        var body = HttpQueryHelper.getBody(exchange);

        try
        {
            var updateCompanyDto = mapper.readValue(body, UpdateCompanyDto.class);
            var result = companyService.UpdateCompany(updateCompanyDto);
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

    private void DeleteCompany(HttpExchange exchange) throws IOException {
        var query = HttpQueryHelper.getQueries(exchange);
        int companyId = Integer.parseInt(query.get("id"));
        var result = companyService.DeleteCompany(companyId);
        String responseJson = mapper.writeValueAsString(result);
        exchange.sendResponseHeaders(result.StatusCode, responseJson.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(responseJson.getBytes());
        output.flush();
    }
}
