package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.requests.CreateJobTitleRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllJobTitleResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdJobTitleResponse;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;

import java.util.List;

public interface JobTitleService {
    DataResult<List<GetAllJobTitleResponse>> getAll();
    DataResult<GetByIdJobTitleResponse> getById(int id);
    Result add(CreateJobTitleRequest createJobTitleRequest);
}
