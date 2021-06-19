package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;

import java.util.List;

public interface JobPostingService {

    Result add(JobPosting jobPosting);

    Result update(JobPosting jobPosting);

    DataResult<List<JobPosting>> getAllByActiveIsTrue();

    DataResult<List<JobPosting>> getAllByActiveIsTrue(boolean ascending);

    DataResult<List<JobPosting>> getAllByActiveAnAndEmployer_Id(int id);

    DataResult<JobPosting> getById(int id);

    Result setActive(JobPosting jobPosting, boolean active);
}
