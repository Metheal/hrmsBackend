package kodlamaio.hrmsbackend.business.rules;


import kodlamaio.hrmsbackend.core.dataAccess.UserDao;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserDao userDao;

    public void checkIfPasswordMatches(User user) {
        if (!user.getPassword().equals(user.getPasswordConfirmation()))
            throw new BusinessException("Parolalar eslesmiyor!");
    }

    public void checkIfEmailIsUnique(String email) {
        var result = this.userDao.existsByEmail(email);
        if (result)
            throw new BusinessException("Bu email adresi ile uye olunmus!");
    }
}