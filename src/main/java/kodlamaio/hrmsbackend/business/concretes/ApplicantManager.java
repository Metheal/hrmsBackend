package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.*;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ApplicantDao;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import kodlamaio.hrmsbackend.entities.concretes.ProfilePicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ApplicantManager implements ApplicantService {
    private ApplicantDao applicantDao;
    private UserService userService;
    private ApplicantCheckService applicantCheckService;
    private VerificationCodeService verificationCodeService;
    private EmailService emailService;
    private ProfilePictureService profilePictureService;

    @Autowired
    public ApplicantManager(ApplicantDao applicantDao, UserService userService, ApplicantCheckService applicantCheckService,
                            VerificationCodeService verificationCodeService, EmailService emailService,
                            ProfilePictureService profilePictureService) {
        this.applicantDao = applicantDao;
        this.userService = userService;
        this.applicantCheckService = applicantCheckService;
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
        this.profilePictureService = profilePictureService;
    }

    @Override
    public DataResult<List<Applicant>> getAll() {
        return new SuccessDataResult<>(this.applicantDao.findAll(), "Tum is arayanlar listelendi");
    }

    @Override
    public DataResult<Applicant> getById(int id) {
        return new SuccessDataResult<>(this.applicantDao.getById(id), "Is arayan getirildi");
    }

    @Override
    public DataResult<Applicant> getByUserId(int id) {
        return new SuccessDataResult<>(this.applicantDao.getByUser_Id(id), "Is arayan kullanici Id'sine gore getirildi");
    }

    @Override
    public DataResult<Applicant> getByUserEmail(String email) {
        return new SuccessDataResult<>(this.applicantDao.getByUser_Email(email), "Is arayan kullanici emailine gore getirildi");
    }

    @Override
    public Result add(Applicant applicant, MultipartFile file) throws Exception {

        var nationalityIdUnique = nationalIdUnique(applicant);
        if (!nationalityIdUnique.isSuccess()) {
            return new ErrorResult(nationalityIdUnique.getMessage());
        }
        var checkIfCitizen = checkIfCitizen(applicant);
        if (!checkIfCitizen.isSuccess()) {
            return new ErrorResult(checkIfCitizen.getMessage());
        }
        var registerUser = registerUser(applicant);
        if (!registerUser.isSuccess()) {
            return new ErrorResult(registerUser.getMessage());
        }

        var result =  this.applicantDao.save(applicant);

        if (file != null) {
            uploadProfilePicture(result, file);
        }
        sendConfirmationEmail(result.getUser());
        return new SuccessResult("Is arayan kaydi olusturuldu");
    }

    private Result uploadProfilePicture(Applicant applicant, MultipartFile file) throws IOException {
        var result = new ProfilePicture();
        result.setApplicant(applicant);
        return this.profilePictureService.add(result, file);
    }

    private Result registerUser(Applicant applicant) throws InterruptedException {
        return this.userService.add(applicant.getUser());
    }

    private Result nationalIdUnique(Applicant applicant) {
        var result = applicantDao.getByNationalId(applicant.getNationalId());
        if (result != null) {
            return new ErrorResult("Bu kimlik numarasiyla uye olunmus");
        }
        return new SuccessResult();
    }

    private Result checkIfCitizen(Applicant applicant) throws Exception {
        var result = this.applicantCheckService.checkIfCitizen(applicant);
        if (!result) {
            return new ErrorResult("Girilen kimlik bilgileri gecerli degil");
        }
        return new SuccessResult();
    }

    private void sendConfirmationEmail(User user) {
        this.emailService.sendEmail(user.getEmail(),
                "E-posta adresinizi dogrulamak ve kaydinizi tamamlamak icin kodu ilgili yere girin: "
                        + this.verificationCodeService.createCode(user).getData());
    }
}
