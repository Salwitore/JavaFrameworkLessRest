package org.example.Business.Services.User;

import org.example.Business.Dtos.User.AddUserDto;
import org.example.Business.Dtos.User.GetUserDto;
import org.example.Business.Dtos.User.UpdateUserDto;
import org.example.Data.Models.Company.CompanyModel;
import org.example.Data.Models.Results.DataResult;
import org.example.Data.Models.Results.Factory.ResultCreater;
import org.example.Data.Models.Results.Result;
import org.example.Data.Models.Results.SuccessDataResult;
import org.example.Data.Models.User.UserModel;
import org.example.DataAccess.Repository.IRepository;
import org.example.DataAccess.UnitOfWork.UnitOfWork;

public class UserService {

    UnitOfWork uow;
    public UserService()
    {
       uow = new UnitOfWork();
    }


    public Result AddUser(AddUserDto addUserDto)
    {
        try
        {
            var userRepository = uow.GetRepository(UserModel.class);
            var companyRepository = uow.GetRepository(CompanyModel.class);

            UserModel userModel = new UserModel(addUserDto.Name, addUserDto.Surname, addUserDto.Username);
            var company = companyRepository.Get(addUserDto.CompanyId);

            if (company == null)
            {
                return ResultCreater.CreateResult(false,"Company not found!" , 404);
            }
            userModel.setCompany(company);


            userRepository.Add(userModel);


            if (userRepository.SaveChanges())
            {
                return ResultCreater.CreateResult(true,"Success!",200);
            }
            else
            {
                return ResultCreater.CreateResult(false,"User not added!",500);
            }
        }
        catch (Exception ex)
        {
            return ResultCreater.CreateExceptionResult(ex);
        }
    }

    public DataResult<GetUserDto> GetUser(int id)
    {
        try
        {
            var userRepository = uow.GetRepository(UserModel.class);
            var user = userRepository.Get(id);
            if (user == null)
            {
                return ResultCreater.CreateDataResult(null,false,"User not found!" , 404);
            }
            GetUserDto result = new GetUserDto(user.getCompany().getId(), user.getId(), user.getName(), user.getSurname(), user.getUsername());
            return ResultCreater.CreateDataResult(result,true,"Success",200);
        }
        catch (Exception ex)
        {
            return ResultCreater.CreateExceptionDataResult(ex);
        }
    }

    public Result DeleteUser(int id)
    {
        try
        {
            var userRepository = uow.GetRepository(UserModel.class);
            var user = userRepository.Get(id);

            if (user == null)
            {
                return ResultCreater.CreateResult(false,"User not found!",404);
            }

            userRepository.Delete(user);

            return userRepository.SaveChanges() ?
                   ResultCreater.CreateResult(true) :
                   ResultCreater.CreateResult(false,"User not deleted!", 500);

        }
        catch (Exception ex)
        {
            return ResultCreater.CreateExceptionResult(ex);
        }
    }

    public Result UpdateUser(UpdateUserDto updateUserDto)
    {
        try
        {
            var userRepository = uow.GetRepository(UserModel.class);

            var user = userRepository.Get(updateUserDto.Id);

            if (user == null)
            {
                return ResultCreater.CreateResult(false,"User not found!" , 404);
            }

            user.setName(updateUserDto.Name);
            user.setSurname(updateUserDto.Surname);
            user.setUsername(updateUserDto.Username);

            userRepository.Update(user);
            return userRepository.SaveChanges() ?
                   ResultCreater.CreateResult(true) :
                   ResultCreater.CreateResult(false,"User not updated!",500);
        }
        catch (Exception ex)
        {
            return ResultCreater.CreateExceptionResult(ex);
        }
    }
}
