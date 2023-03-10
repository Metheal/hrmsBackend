package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.responses.GetAllByApplicantIdResumeResponse;
import kodlamaio.hrmsbackend.business.responses.GetAllResumeResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdResumeResponse;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Resume;

import java.util.List;

public interface ResumeService {
    DataResult<List<GetAllResumeResponse>> getAll();

    DataResult<List<GetAllByApplicantIdResumeResponse>> getAllByApplicant_Id(int id);

    DataResult<GetByIdResumeResponse> getById(int id);

    Result add(Resume resume);
}
