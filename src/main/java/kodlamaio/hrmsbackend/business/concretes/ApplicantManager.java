package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantCheckService;
import kodlamaio.hrmsbackend.business.abstracts.ApplicantService;
import kodlamaio.hrmsbackend.business.abstracts.EmailService;
import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.core.entities.concretes.User;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ApplicantDao;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantManager implements ApplicantService {
    private ApplicantDao applicantDao;
    private UserService userService;
    private ApplicantCheckService applicantCheckService;
    private VerificationCodeService verificationCodeService;
    private EmailService emailService;

    @Autowired
    public ApplicantManager(ApplicantDao applicantDao, UserService userService, ApplicantCheckService applicantCheckService,
                            VerificationCodeService verificationCodeService, EmailService emailService) {
        this.applicantDao = applicantDao;
        this.userService = userService;
        this.applicantCheckService = applicantCheckService;
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<Applicant>> getAll() {
        return new SuccessDataResult<>(this.applicantDao.findAll(), "Tum is arayanlar listelendi");
    }

    @Override
    public Result add(Applicant applicant) throws Exception {
        var checkIfFieldsAreEmpty = checkIfFieldsAreEmpty(applicant);
        if (!checkIfFieldsAreEmpty.isSuccess()) {
            return new ErrorResult(checkIfFieldsAreEmpty.getMessage());
        }
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
        this.applicantDao.save(applicant);
        sendConfirmationEmail(applicant.getUser());
        return new SuccessResult("Is arayan kaydi olusturuldu");
    }

    private Result registerUser(Applicant applicant) throws InterruptedException {
        return this.userService.add(applicant.getUser());
    }

    private Result nationalIdUnique(Applicant applicant) {
        var result = applicantDao.findByNationalId(applicant.getNationalId());
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

    private Result checkIfFieldsAreEmpty(Applicant applicant) {
        if (applicant.getNationalId().isEmpty()) {
            return new ErrorResult("TC Kimlik numarasi bos birakilamaz");
        } else if (applicant.getDateOfBirth().toString().isEmpty()) {
            return new ErrorResult("Dogum tarihi bos birakilamaz");
        } else if (applicant.getFirstName().isEmpty()) {
            return new ErrorResult("Isim bos birakilamaz");
        } else if (applicant.getLastName().isEmpty()) {
            return new ErrorResult("Soyisim bos birakilamaz");
        }
        return new SuccessResult();
    }

    private void sendConfirmationEmail(User user) {
        this.emailService.sendEmail(user.getEmail(),
                "E-posta adresinizi dogrulamak ve kaydinizi tamamlamak icin kodu ilgili yere girin: "
                        + this.verificationCodeService.createCode(user).getData());
    }
}
