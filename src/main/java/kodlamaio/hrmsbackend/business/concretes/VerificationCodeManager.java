package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.business.requests.VerifyApplicantRequest;
import kodlamaio.hrmsbackend.business.requests.VerifyCorporateRequest;
import kodlamaio.hrmsbackend.business.requests.VerifyUserRequest;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ApplicantDao;
import kodlamaio.hrmsbackend.dataAccess.abstracts.CorporateDao;
import kodlamaio.hrmsbackend.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import kodlamaio.hrmsbackend.entities.concretes.VerificationCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class VerificationCodeManager implements VerificationCodeService {
    private final VerificationCodeDao verificationCodeDao;
    private final UserService userService;
    private final ApplicantDao applicantDao;
    private final CorporateDao corporateDao;

    @Override
    public DataResult<String> createCode(User user) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 32) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String code = salt.toString();

        var minutes = 10;
        new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.MINUTES.toMillis(minutes));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deleteCode(code);
        }).start();

        var verificationCode = new VerificationCode();
        verificationCode.setUserId(user.getId());
        verificationCode.setCode(code);
        this.verificationCodeDao.save(verificationCode);
        return new SuccessDataResult<>(code, user.getEmail() + " icin kod olusturuldu. " + minutes + " dk icinde silinecek");
    }

    @Override
    public Result verifyApplicant(VerifyApplicantRequest verifyApplicantRequest, String code) {
        int userId = this.applicantDao.getById(verifyApplicantRequest.getId()).getUser().getId();
        var verificationCode = this.verificationCodeDao.getVerificationCodeByUserId(userId);
        if (verificationCode == null || !verificationCode.getCode().equals(code)) {
            return new ErrorResult("Dogrulama basarisiz");
        }
        var userToUpdate =  this.userService.getUserById(userId).getData();
        userToUpdate.setEmailVerified(true);
        userToUpdate.setActive(true);
        userService.update(userToUpdate);
        deleteCode(code);
        return new SuccessResult("Dogrulama basarili");
    }

    @Override
    public Result verifyCorporate(VerifyCorporateRequest verifyCorporateRequest, String code) {
        int userId = this.corporateDao.getById(verifyCorporateRequest.getId()).getUser().getId();
        var verificationCode = this.verificationCodeDao.getVerificationCodeByUserId(userId);
        if (verificationCode == null || !verificationCode.getCode().equals(code)) {
            return new ErrorResult("Dogrulama basarisiz");
        }
        var userToUpdate = this.userService.getUserById(userId).getData();
        userToUpdate.setEmailVerified(true);
        userService.update(userToUpdate);
        deleteCode(code);
        return new SuccessResult("Dogrulama basarili");
    }

    @Override
    public Result deleteCode(String code) {
        var verificationCode = this.verificationCodeDao.getVerificationCodeByCode(code);
        this.verificationCodeDao.delete(verificationCode);
        return new SuccessResult("Dogrulama kodu kaydi silindi");
    }
}
