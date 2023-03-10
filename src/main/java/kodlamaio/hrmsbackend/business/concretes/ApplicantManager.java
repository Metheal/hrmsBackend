package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantService;
import kodlamaio.hrmsbackend.business.abstracts.EmailService;
import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.business.requests.CreateApplicantRequest;
import kodlamaio.hrmsbackend.business.requests.CreateUserRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByEmailApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByUserIdApplicantResponse;
import kodlamaio.hrmsbackend.business.rules.ApplicantBusinessRules;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.mappers.ModelMapperService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ApplicantDao;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicantManager implements ApplicantService {
    private final ApplicantDao applicantDao;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;
    private final ModelMapperService modelMapperService;
    private final ApplicantBusinessRules applicantBusinessRules;
    private final UserService userService;

    @Override
    public DataResult<List<GetAllApplicantResponse>> getAll() {
        List<Applicant> applicants = this.applicantDao.findAll();
        List<GetAllApplicantResponse> applicantResponses = applicants.stream()
                .map(applicant -> this.modelMapperService.forResponse()
                        .map(applicant, GetAllApplicantResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(applicantResponses, "Tum is arayanlar listelendi");
    }

    @Override
    public DataResult<GetByIdApplicantResponse> getById(int id) {
        var applicant = this.applicantDao.findById(id);
        if (applicant.isEmpty()) {
            return new ErrorDataResult<>("Is arayan bulunamadi!");
        }

        GetByIdApplicantResponse getByIdApplicantResponse
                = this.modelMapperService.forResponse().map(applicant.get(), GetByIdApplicantResponse.class);
        return new SuccessDataResult<>(getByIdApplicantResponse, "Is arayan getirildi");
    }

    @Override
    public DataResult<GetByUserIdApplicantResponse> getByUserId(int id) {
        Applicant applicant = this.applicantDao.findByUser_Id(id);
        if (applicant == null)
            return new ErrorDataResult<>("Is arayan bu kullanici Id ile bulunamadi!");

        GetByUserIdApplicantResponse getByUserIdApplicantResponse
                = this.modelMapperService.forResponse().map(applicant, GetByUserIdApplicantResponse.class);
        return new SuccessDataResult<>(getByUserIdApplicantResponse, "Is arayan kullanici Id'sine gore getirildi");
    }

    @Override
    public DataResult<GetByEmailApplicantResponse> getByUserEmail(String email) {
        Applicant applicant = this.applicantDao.getByUser_Email(email);
        if (applicant == null)
            return new ErrorDataResult<>("Is arayan bu email ile bulunamadi!");

        GetByEmailApplicantResponse getByEmailApplicantResponse
                = this.modelMapperService.forResponse().map(applicant, GetByEmailApplicantResponse.class);
        return new SuccessDataResult<>(getByEmailApplicantResponse, "Is arayan email adresine gore getirildi");
    }

    @Override
    public Result add(CreateApplicantRequest createApplicantRequest, @Nullable MultipartFile file) throws Exception {
        Applicant applicant = this.modelMapperService.forRequest().map(createApplicantRequest, Applicant.class);
        this.applicantBusinessRules.checkIfNationalIdIsUnique(createApplicantRequest.getNationalId());
        this.applicantBusinessRules.checkIfCitizen(applicant);
        this.applicantBusinessRules.checkIfFileProvided(applicant, file);
        CreateUserRequest createUserRequest = this.modelMapperService.forRequest().map(createApplicantRequest, CreateUserRequest.class);

        var user = this.userService.registerUserAndReturn(createUserRequest).getData();
        applicant.setUser(user);
        var result = this.applicantDao.save(applicant);
        var message = sendConfirmationEmail(result.getUser());
        return new SuccessResult(message);
    }

    private String sendConfirmationEmail(User user) {
        var code = this.verificationCodeService.createCode(user).getData();
        var message = "E-posta adresinizi dogrulamak ve kaydinizi tamamlamak icin kodu ilgili yere girin: ";
        this.emailService.sendEmail(user.getEmail(), message + code);
        return message + code;
    }
}
