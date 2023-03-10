package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.requests.CreateUserRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllUserResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdUserResponse;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;

import java.util.List;

public interface UserService {
    DataResult<List<GetAllUserResponse>> getAll();

    DataResult<GetByIdUserResponse> getById(int id);

    DataResult<User> getUserById(int id);

    DataResult<User> getByEmail(String email);

    DataResult<User> registerUserAndReturn(CreateUserRequest createUserRequest);

    Result add(CreateUserRequest createUserRequest) throws InterruptedException;

    Result update(User user);

}
