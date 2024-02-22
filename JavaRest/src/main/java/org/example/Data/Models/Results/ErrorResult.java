package org.example.Data.Models.Results;

public class ErrorResult extends Result
{
    public ErrorResult()
    {
        IsSuccess = false;
        Message = "Error!";
        StatusCode = 500;
    }

    public ErrorResult(String message , int statusCode)
    {
        IsSuccess = false;
        Message = message;
        StatusCode = statusCode;
    }
}
