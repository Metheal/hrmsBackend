package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.EmailService;
import kodlamaio.hrmsbackend.business.abstracts.EmployerService;
import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.core.entities.concretes.User;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrmsbackend.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;
    private UserService userService;
    private VerificationCodeService verificationCodeService;
    private EmailService emailService;

    @Autowired
    public EmployerManager(EmployerDao employerDao, UserService userService,
                           VerificationCodeService verificationCodeService, EmailService emailService) {
        this.employerDao = employerDao;
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(this.employerDao.findAll(), "Tum isverenler listelendi");
    }

    @Override
    public Result add(Employer employer) throws InterruptedException {
        var checkIfFieldsAreEmpty = checkIfFieldsAreEmpty(employer);
        if (!checkIfFieldsAreEmpty.isSuccess()) {
            return new ErrorResult(checkIfFieldsAreEmpty.getMessage());
        }
        var checkIfEmailMatchesDomain = checkIfEmailMatchesDomain(employer);
        if (!checkIfEmailMatchesDomain.isSuccess()) {
            return new ErrorResult(checkIfEmailMatchesDomain.getMessage());
        }
        var registerUser = registerUser(employer);
        if (!registerUser.isSuccess()) {
            return new ErrorResult(registerUser.getMessage());
        }
        this.employerDao.save(employer);
        sendConfirmationEmail(employer.getUser());
        return new SuccessResult("Isveren kaydi olusturuldu");
    }

    @Override
    public Result setActive(Employer employer, boolean active) {
        var employerToUpdate = this.employerDao.getById(employer.getId());
        employerToUpdate.getUser().setActive(active);
        employerDao.save(employerToUpdate);
        return new SuccessResult("Basariyla guncellendi");
    }

    private Result checkIfEmailMatchesDomain(Employer employer) {
        var domain = employer.getUser().getEmail().split("@")[1];
        var result = employer.getWebsiteUrl().equals(domain);
        if (!result) {
            return new ErrorResult("Email adresi ile web sitesi eslesmiyor");
        }
        return new SuccessResult();
    }

    private Result registerUser(Employer employer) throws InterruptedException {
        return this.userService.add(employer.getUser());
    }

    private Result checkIfFieldsAreEmpty(Employer employer) {
        if (employer.getCompanyName().isEmpty()) {
            return new ErrorResult("Sirket ismi bos birakilamaz");
        } else if (employer.getPhoneNumber().isEmpty()) {
            return new ErrorResult("Telefon numarasi bos birakilamaz");
        } else if (employer.getWebsiteUrl().isEmpty()) {
            return new ErrorResult("Website adresi bos birakilamaz");
        } else if (employer.getUser().getEmail().isEmpty()) {
            return new ErrorResult("Email adresi bos birakilamaz");
        }
        return new SuccessResult();
    }

    private void sendConfirmationEmail(User user){
        this.emailService.sendEmail(user.getEmail(), "E-posta adresinizi dogrulamak icin kodu ilgili yere girin: "
                + this.verificationCodeService.createCode(user).getData());
    }


}
