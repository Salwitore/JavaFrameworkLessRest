package org.example.Data.Models.Results;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(T data)
    {
        IsSuccess = false;
        Data = data;
        Message = "Error!";
        StatusCode = 500;
    }

    public ErrorDataResult(T data , String message , int statusCode)
    {
        IsSuccess = false;
        Data = data;
        Message = message;
        StatusCode = statusCode;
    }

}
