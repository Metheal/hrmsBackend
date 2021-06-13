package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.entities.concretes.JobTitle;

import java.util.List;

public interface JobTitleService {
    DataResult<List<JobTitle>> getAll();
}
