package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Employer;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();

    Result add(Employer employer) throws InterruptedException;

    Result setActive(Employer employer, boolean active);
}
