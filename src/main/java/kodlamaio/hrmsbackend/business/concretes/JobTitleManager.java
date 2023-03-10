package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.JobTitleService;
import kodlamaio.hrmsbackend.business.requests.CreateJobTitleRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllJobTitleResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdJobTitleResponse;
import kodlamaio.hrmsbackend.business.rules.JobTitleBusinessRules;
import kodlamaio.hrmsbackend.core.utilities.mappers.ModelMapperService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrmsbackend.entities.concretes.JobTitle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobTitleManager implements JobTitleService {
    private final JobTitleDao jobTitleDao;
    private final ModelMapperService modelMapperService;
    private final JobTitleBusinessRules jobTitleBusinessRules;

    @Override
    public DataResult<List<GetAllJobTitleResponse>> getAll() {
        List<JobTitle> jobTitles = this.jobTitleDao.findAll();
        List<GetAllJobTitleResponse> getAllJobTitleResponses = jobTitles.stream().map(jobTitle -> this.modelMapperService.forResponse().map(jobTitle, GetAllJobTitleResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(getAllJobTitleResponses, "Is pozisyonlari listelendi");
    }

    @Override
    public DataResult<GetByIdJobTitleResponse> getById(int id) {
        var jobTitle = this.jobTitleDao.findById(id);
        if (jobTitle.isEmpty())
            return new ErrorDataResult<>("Is ilani bulunamadi!");

        GetByIdJobTitleResponse getByIdJobTitleResponse = this.modelMapperService.forResponse().map(jobTitle, GetByIdJobTitleResponse.class);
        return new SuccessDataResult<>(getByIdJobTitleResponse, "Is ilani Id'sine gore getirildi");

    }

    @Override
    public Result add(CreateJobTitleRequest createJobTitleRequest) {
        JobTitle jobTitle = this.modelMapperService.forRequest().map(createJobTitleRequest, JobTitle.class);
        this.jobTitleBusinessRules.checkIfJobTitleExists(jobTitle.getName());

        this.jobTitleDao.save(jobTitle);
        return new SuccessResult("Is pozisyonu eklendi " + jobTitle.getName());
    }
}
