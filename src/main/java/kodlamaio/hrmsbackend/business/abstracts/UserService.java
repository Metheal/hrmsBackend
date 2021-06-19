package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.entities.concretes.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();

    DataResult<User> getById(int id);

    Result add(User user) throws InterruptedException;

    Result update(User user);
}
