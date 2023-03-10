package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.business.requests.CreateUserRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllUserResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdUserResponse;
import kodlamaio.hrmsbackend.business.rules.UserBusinessRules;
import kodlamaio.hrmsbackend.core.dataAccess.UserDao;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.mappers.ModelMapperService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserDao userDao;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRules userBusinessRules;

    @Override
    public DataResult<List<GetAllUserResponse>> getAll() {
        List<User> users = this.userDao.findAll();
        List<GetAllUserResponse> getAllUserResponses = users.stream().map(user -> this.modelMapperService.forResponse().map(user, GetAllUserResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(getAllUserResponses, "Tum kullanicilar listelendi");
    }

    @Override
    public DataResult<GetByIdUserResponse> getById(int id) {
        var user = this.userDao.findById(id);
        if (user.isEmpty())
            return new ErrorDataResult<>("Kullanici bulunamadi!");

        GetByIdUserResponse getByIdUserResponse = this.modelMapperService.forResponse().map(user.get(), GetByIdUserResponse.class);
        return new SuccessDataResult<>(getByIdUserResponse, "Kullanici getirildi");
    }

    @Override
    public DataResult<User> getUserById(int id) {
        var user = this.userDao.findById(id);
        if (user.isEmpty())
            return new ErrorDataResult<>("Kullanici bulunamadi!");

        return new SuccessDataResult<>(user.get(), "Kullanici getirildi");
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        var data = this.userDao.getByEmail(email);
        return (data == null)
                ? new ErrorDataResult<>("Kullanici bulunamadi!")
                : new SuccessDataResult<>(data, "Kullanici email adresine gore getirildi");
    }

    @Override
    public DataResult<User> registerUserAndReturn(CreateUserRequest createUserRequest) {
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userBusinessRules.checkIfPasswordMatches(user);
        this.userBusinessRules.checkIfEmailIsUnique(user.getEmail());

        var newUser = newUser(user);
        this.userDao.save(newUser);
        return new SuccessDataResult<>(this.userDao.getByEmail(user.getEmail()), "Kullanici eklendi");
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) throws InterruptedException {
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userBusinessRules.checkIfPasswordMatches(user);
        this.userBusinessRules.checkIfEmailIsUnique(user.getEmail());

        var newUser = newUser(user);
        this.userDao.save(newUser);
        return new SuccessResult("Kullanici eklendi");
    }

    @Override
    public Result update(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanici guncellendi");
    }

    private User newUser(User user) {
        user.setActive(false);
        user.setEmailVerified(false);
        user.setRegisterDate(LocalDate.now());
        return user;
    }
}
