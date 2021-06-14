package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.core.entities.concretes.User;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getall() {
        return new SuccessDataResult<>(this.userDao.findAll(), "Tum kullanicilar listelendi");
    }

    @Override
    public DataResult<User> getById(int id) {
        return new SuccessDataResult<>(this.userDao.findById(id), "Kullanici getirildi");
    }

    @Override
    public Result add(User user) throws InterruptedException {
        var newUser = newUser(user);
        var checkIfFieldsAreEmpty = checkIfFieldsAreEmpty(user);
        if (!checkIfFieldsAreEmpty.isSuccess()) {
            return new ErrorResult(checkIfFieldsAreEmpty.getMessage());
        }
        var passwordMatches = passwordMatches(newUser);
        if (!passwordMatches.isSuccess()) {
            return new ErrorResult(passwordMatches.getMessage());
        }
        var emailUnique = emailUnique(newUser);
        if (!emailUnique.isSuccess()) {
            return new ErrorResult(emailUnique.getMessage());
        }
        this.userDao.save(newUser);
        return new SuccessResult("Kullanici eklendi");
    }

    @Override
    public Result update(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanici guncellendi");
    }

    private Result passwordMatches(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            return new ErrorResult("Parola dogrulamasi basarisiz");
        }
        return new SuccessResult();
    }

    private Result emailUnique(User user) {
        var result = this.userDao.findByEmail(user.getEmail());
        if (result != null) {
            return new ErrorResult("Bu email adresiyle daha once kayit olunmus");
        }
        return new SuccessResult();
    }

    private User newUser(User user) {
        user.setActive(false);
        user.setEmailVerified(false);
        user.setRegisterDate(LocalDate.now());
        return user;
    }

    private Result checkIfFieldsAreEmpty(User user) {
        if (user.getEmail().isEmpty()) {
            return new ErrorResult("Email kismi bos birakilamaz");
        } else if (user.getPassword().isEmpty()) {
            return new ErrorResult("Parola kismi bos birakilamaz");
        } else if (user.getPasswordConfirmation().isEmpty()) {
            return new ErrorResult("Parola dogrulama kismi bos birakilamaz");
        }
        return new SuccessResult();
    }
}
