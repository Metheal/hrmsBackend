package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.CorporateService;
import kodlamaio.hrmsbackend.business.abstracts.EmailService;
import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.CorporateDao;
import kodlamaio.hrmsbackend.entities.concretes.Corporate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateManager implements CorporateService {

    private CorporateDao corporateDao;
    private UserService userService;
    private VerificationCodeService verificationCodeService;
    private EmailService emailService;

    @Autowired
    public CorporateManager(CorporateDao corporateDao, UserService userService,
                            VerificationCodeService verificationCodeService, EmailService emailService) {
        this.corporateDao = corporateDao;
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<Corporate>> getAll() {
        return new SuccessDataResult<>(this.corporateDao.findAll(), "Tum isverenler listelendi");
    }

    @Override
    public DataResult<Corporate> getById(int id) {
        return new SuccessDataResult<>(this.corporateDao.findById(id).get(), "Isveren getirildi");
    }

    @Override
    public DataResult<Corporate> getByUserId(int id) {
        return new SuccessDataResult<>(this.corporateDao.getByUser_Id(id), "Isveren Kullanici Id'sine gore getirildi");
    }

    @Override
    public DataResult<Corporate> getByUserEmail(String email) {
        return new SuccessDataResult<>(this.corporateDao.getByUser_Email(email), "Isveren kullanici emailine gore getirildi");
    }

    @Override
    public Result add(Corporate corporate) throws InterruptedException {
        var checkIfEmailMatchesDomain = checkIfEmailMatchesDomain(corporate);
        if (!checkIfEmailMatchesDomain.isSuccess()) {
            return new ErrorResult(checkIfEmailMatchesDomain.getMessage());
        }
        var registerUser = registerUser(corporate);
        if (!registerUser.isSuccess()) {
            return new ErrorResult(registerUser.getMessage());
        }
        this.corporateDao.save(corporate);
        sendConfirmationEmail(corporate.getUser());
        return new SuccessResult("Isveren kaydi olusturuldu");
    }

    @Override
    public Result setActive(Corporate corporate, boolean active) {
        var corporateToUpdate = this.corporateDao.getById(corporate.getId());
        corporateToUpdate.getUser().setActive(active);
        corporateDao.save(corporateToUpdate);
        return new SuccessResult("Basariyla guncellendi");
    }

    private Result checkIfEmailMatchesDomain(Corporate corporate) {
        var domainFromEmail = corporate.getUser().getEmail().split("@")[1];
        var result = corporate.getWebsiteUrl().contains(domainFromEmail);
        if (!result) {
            return new ErrorResult("Email adresi ile web sitesi eslesmiyor");
        }
        return new SuccessResult();
    }

    private Result registerUser(Corporate corporate) throws InterruptedException {
        return this.userService.add(corporate.getUser());
    }

    private void sendConfirmationEmail(User user) {
        this.emailService.sendEmail(user.getEmail(), "E-posta adresinizi dogrulamak icin kodu ilgili yere girin: "
                + this.verificationCodeService.createCode(user).getData());
    }


}
