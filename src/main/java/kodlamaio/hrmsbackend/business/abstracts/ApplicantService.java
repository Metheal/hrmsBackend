package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;

import java.util.List;

public interface ApplicantService {
    DataResult<List<Applicant>> getAll();

    Result add(Applicant applicant) throws Exception;
}
