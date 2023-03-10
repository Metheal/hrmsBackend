package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.CorporateService;
import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.business.requests.CreateCorporateRequest;
import kodlamaio.hrmsbackend.business.requests.CreateUserRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllCorporateResponse;
import kodlamaio.hrmsbackend.business.responses.GetByEmailCorporateResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdCorporateResponse;
import kodlamaio.hrmsbackend.business.responses.GetByUserIdCorporateResponse;
import kodlamaio.hrmsbackend.business.rules.CorporateBusinessRules;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.mappers.ModelMapperService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.CorporateDao;
import kodlamaio.hrmsbackend.entities.concretes.Corporate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CorporateManager implements CorporateService {
    private final ModelMapperService modelMapperService;
    private final CorporateDao corporateDao;
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;
    private final CorporateBusinessRules corporateBusinessRules;

    @Override
    public DataResult<List<GetAllCorporateResponse>> getAll() {
        List<Corporate> corporates = this.corporateDao.findAll();
        List<GetAllCorporateResponse> getAllCorporateResponseList = corporates.stream()
                .map(corporate -> this.modelMapperService.forResponse()
                        .map(corporate, GetAllCorporateResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(getAllCorporateResponseList, "Tum isverenler listelendi");
    }

    @Override
    public DataResult<GetByIdCorporateResponse> getById(int id) {
        var corporate = this.corporateDao.findById(id);
        if (corporate.isEmpty())
            return new ErrorDataResult<>("Isveren bulunamadi");

        GetByIdCorporateResponse getByIdCorporateResponse = this.modelMapperService.forResponse().map(corporate.get(), GetByIdCorporateResponse.class);
        return new SuccessDataResult<>(getByIdCorporateResponse, "Isveren getirildi");
    }

    @Override
    public DataResult<GetByUserIdCorporateResponse> getByUserId(int id) {
        Corporate corporate = this.corporateDao.getByUser_Id(id);
        if (corporate == null)
            return new ErrorDataResult<>("Is veren bu kullanici Id ile bulunamadi!");

        GetByUserIdCorporateResponse getByUserIdCorporateResponse
                = this.modelMapperService.forResponse().map(corporate, GetByUserIdCorporateResponse.class);
        return new SuccessDataResult<>(getByUserIdCorporateResponse, "Isveren Kullanici Id'sine gore getirildi");
    }

    @Override
    public DataResult<GetByEmailCorporateResponse> getByUserEmail(String email) {
        Corporate corporate = this.corporateDao.getByUser_Email(email);
        if (corporate == null)
            return new ErrorDataResult<>("Is veren bu email ile bulunamadi!");

        GetByEmailCorporateResponse getByEmailCorporateResponse
                = this.modelMapperService.forResponse().map(corporate, GetByEmailCorporateResponse.class);
        return new SuccessDataResult<>(getByEmailCorporateResponse, "Isveren kullanici emailine gore getirildi");
    }

    @Override
    public Result add(CreateCorporateRequest createCorporateRequest) throws InterruptedException {
        Corporate corporate = this.modelMapperService.forRequest().map(createCorporateRequest, Corporate.class);
        CreateUserRequest createUserRequest = this.modelMapperService.forRequest().map(createCorporateRequest, CreateUserRequest.class);
        corporateBusinessRules.checkIfEmailMatchesDomain(createCorporateRequest);
        corporateBusinessRules.checkIfCompanyNameIsUnique(createCorporateRequest);

        var registerUser = registerUser(createUserRequest);
        corporate.setUser(registerUser.getData());

        this.corporateDao.save(corporate);
        this.verificationCodeService.createCode(corporate.getUser());
        return new SuccessResult("Sirket basariyla kaydedildi");
    }

    @Override
    public Result setActive(Corporate corporate, boolean active) {
        var corporateToUpdate = this.corporateDao.getById(corporate.getId());
        corporateToUpdate.getUser().setActive(active);
        corporateDao.save(corporateToUpdate);
        return new SuccessResult("Basariyla guncellendi");
    }

    private DataResult<User> registerUser(CreateUserRequest createUserRequest) {
        return this.userService.registerUserAndReturn(createUserRequest);
    }
}
