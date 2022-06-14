package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Resume;

import java.util.List;

public interface ResumeService {
    DataResult<List<Resume>> getAll();

    DataResult<List<Resume>> getAllByApplicant_Id(int id);

    DataResult<Resume> getById(int id);

    Result add(Resume resume);
}
