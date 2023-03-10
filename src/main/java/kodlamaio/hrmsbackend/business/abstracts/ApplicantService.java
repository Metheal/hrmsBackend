package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.requests.CreateApplicantRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByEmailApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByUserIdApplicantResponse;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicantService {
    DataResult<List<GetAllApplicantResponse>> getAll();

    DataResult<GetByIdApplicantResponse> getById(int id);

    DataResult<GetByUserIdApplicantResponse> getByUserId(int id);

    DataResult<GetByEmailApplicantResponse> getByUserEmail(String email);

    Result add(CreateApplicantRequest createApplicantRequest, @Nullable MultipartFile file) throws Exception;
}
