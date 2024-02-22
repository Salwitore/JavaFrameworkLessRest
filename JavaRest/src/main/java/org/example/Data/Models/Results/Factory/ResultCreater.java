package org.example.Data.Models.Results.Factory;

import org.example.Data.Models.Results.*;

public class ResultCreater
{
    public static Result CreateResult(boolean isSuccess)
    {
        return isSuccess ? new SuccessResult() : new ErrorResult();
    }

    public static Result CreateResult(boolean isSuccess , String message , int statusCode)
    {
        return isSuccess ? new SuccessResult(message,statusCode) : new ErrorResult(message,statusCode);
    }

    public static <T> DataResult<T> CreateDataResult(T data , boolean isSuccess)
    {
        return isSuccess ? new SuccessDataResult<T>(data) : new ErrorDataResult<T>(data);
    }

    public static <T> DataResult<T> CreateDataResult(T data , boolean isSuccess , String message , int statusCode)
    {
        return isSuccess ? new SuccessDataResult<T>(data,message,statusCode) : new ErrorDataResult<T>(data, message, statusCode);
    }

    public static <T> DataResult<T> CreateExceptionDataResult(Exception ex)
    {
        return new ErrorDataResult<T>(null,ex.getMessage(),500);
    }

    public static Result CreateExceptionResult(Exception ex)
    {
        return new ErrorResult(ex.getMessage(),500);
    }
}
