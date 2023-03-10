package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.requests.CreateCorporateRequest;
import kodlamaio.hrmsbackend.business.responses.*;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Corporate;

import java.util.List;

public interface CorporateService {
    DataResult<List<GetAllCorporateResponse>> getAll();

    DataResult<GetByIdCorporateResponse> getById(int id);

    DataResult<GetByUserIdCorporateResponse> getByUserId(int id);

    DataResult<GetByEmailCorporateResponse> getByUserEmail(String email);

    Result add(CreateCorporateRequest createCorporateRequest) throws InterruptedException;

    Result setActive(Corporate corporate, boolean active);
}
