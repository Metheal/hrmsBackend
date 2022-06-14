package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Corporate;

import java.util.List;

public interface CorporateService {
    DataResult<List<Corporate>> getAll();

    DataResult<Corporate> getById(int id);

    DataResult<Corporate> getByUserId(int id);

    DataResult<Corporate> getByUserEmail(String email);

    Result add(Corporate corporate) throws InterruptedException;

    Result setActive(Corporate corporate, boolean active);
}
