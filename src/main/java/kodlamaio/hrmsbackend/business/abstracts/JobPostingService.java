package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.requests.CreateJobPostingRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllJobPostingResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdJobPosting;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;

import java.util.List;

public interface JobPostingService {

    Result add(CreateJobPostingRequest createJobPostingRequest);

    Result update(JobPosting jobPosting);

    DataResult<List<GetAllJobPostingResponse>> getAllByActiveIsTrue();

    DataResult<List<JobPosting>> getAllByActiveIsTrue(boolean ascending);

    DataResult<List<JobPosting>> getAllByActiveAnAndCorporate_Id(int id);

    DataResult<GetByIdJobPosting> getById(int id);

    Result setActive(JobPosting jobPosting, boolean active);
}
