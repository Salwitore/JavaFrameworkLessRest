package org.example.Data.Models.Results;

public class SuccessResult extends Result
{
    public SuccessResult()
    {
        IsSuccess = true;
        Message = "Success";
        StatusCode = 200;
    }

    public SuccessResult(String message , int statusCode)
    {
        IsSuccess = true;
        Message = message;
        StatusCode = statusCode;
    }
}
