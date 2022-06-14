package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicantService {
    DataResult<List<Applicant>> getAll();

    DataResult<Applicant> getById(int id);

    DataResult<Applicant> getByUserId(int id);

    DataResult<Applicant> getByUserEmail(String email);

    Result add(Applicant applicant, MultipartFile file) throws Exception;
}
