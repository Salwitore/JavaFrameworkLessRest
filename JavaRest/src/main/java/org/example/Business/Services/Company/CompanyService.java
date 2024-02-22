package org.example.Business.Services.Company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Business.Dtos.Company.AddCompanyDto;
import org.example.Business.Dtos.Company.GetCompanyDto;
import org.example.Business.Dtos.Company.UpdateCompanyDto;
import org.example.Data.Models.Company.CompanyModel;
import org.example.Data.Models.Results.DataResult;
import org.example.Data.Models.Results.ErrorDataResult;
import org.example.Data.Models.Results.Factory.ResultCreater;
import org.example.Data.Models.Results.Result;
import org.example.Data.Models.User.UserModel;
import org.example.DataAccess.Repository.IRepository;
import org.example.DataAccess.Repository.Repository;
import org.example.DataAccess.UnitOfWork.UnitOfWork;

public class CompanyService {

    private UnitOfWork uow;
    private ObjectMapper mapper;

    public CompanyService() {
        uow = new UnitOfWork();
        mapper = new ObjectMapper();
    }

    public Result AddCompany(AddCompanyDto addCompanyDto) {
        try {
            CompanyModel companyModel = new CompanyModel(addCompanyDto.Name);
            var companyRepository = uow.GetRepository(CompanyModel.class);
            companyRepository.Add(companyModel);

            boolean result = companyRepository.SaveChanges();
            return result ? ResultCreater.CreateResult(true, "Success!", 200)
                    : ResultCreater.CreateResult(false, "Company not created!", 500);
        } catch (Exception ex) {
            return ResultCreater.CreateExceptionResult(ex);
        }


    }

    public DataResult<GetCompanyDto> GetCompany(int id) {
        try {
            var companyRepository = uow.GetRepository(CompanyModel.class);
            var companyModel = companyRepository.Get(id);

            if (companyModel == null) {
                return ResultCreater.CreateDataResult(null, false, "Company not found!", 404);
            }
            GetCompanyDto getCompanyDto = new GetCompanyDto(companyModel.getId(), companyModel.getName());

            return ResultCreater.CreateDataResult(getCompanyDto, true);
        } catch (Exception ex) {
            return ResultCreater.CreateExceptionDataResult(ex);
        }
    }

    public Result UpdateCompany(UpdateCompanyDto updateCompanyDto) {
        try {
            var companyRepository = uow.GetRepository(CompanyModel.class);

            var updatedCompanyModel = new CompanyModel(updateCompanyDto.Id, updateCompanyDto.Name);

            companyRepository.Update(updatedCompanyModel);

            return companyRepository.SaveChanges() ?
                    ResultCreater.CreateResult(true, "Success", 200) :
                    ResultCreater.CreateResult(false, "Not updated!", 400);


        } catch (Exception ex) {
            return ResultCreater.CreateExceptionResult(ex);
        }
    }

    public Result DeleteCompany(int id) {
        try {
            var companyRepository = uow.GetRepository(CompanyModel.class);

            var company = companyRepository.Get(id);

            if (company == null)
            {
                return ResultCreater.CreateResult(false,"Company not found!",404);
            }
            else
            {
                companyRepository.Delete(company);
                return companyRepository.SaveChanges() ?
                       ResultCreater.CreateResult(true,"Success!" , 200) :
                       ResultCreater.CreateResult(false , "Error!" , 500);
            }

        } catch (Exception ex) {
            return ResultCreater.CreateExceptionResult(ex);
        }
    }

}
