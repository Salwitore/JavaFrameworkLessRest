package org.example.Data.Models.Results;

public class SuccessDataResult<T> extends DataResult<T>
{

    public SuccessDataResult(T data)
    {
        IsSuccess = true;
        Data = data;
        Message = "Success";
        StatusCode = 200;
    }

    public SuccessDataResult(T data , String message , int statusCode)
    {
        IsSuccess = true;
        Data = data;
        Message = message;
        StatusCode = statusCode;
    }

}
