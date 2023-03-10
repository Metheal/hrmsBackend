package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.JobPostingService;
import kodlamaio.hrmsbackend.business.requests.CreateJobPostingRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllJobPostingResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdJobPosting;
import kodlamaio.hrmsbackend.core.utilities.mappers.ModelMapperService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobPostingManager implements JobPostingService {
    private final JobPostingDao jobPostingDao;
    private final ModelMapperService modelMapperService;

    @Override
    public Result add(CreateJobPostingRequest createJobPostingRequest) {
        JobPosting jobPosting = this.modelMapperService.forRequest().map(createJobPostingRequest, JobPosting.class);
        jobPosting.setReleaseDate(LocalDate.now());
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Is ilani eklendi");
    }

    @Override
    public Result update(JobPosting jobPosting) {
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("Is ilani guncellendi");
    }

    @Override
    public DataResult<List<GetAllJobPostingResponse>> getAllByActiveIsTrue() {
        List<JobPosting> jobPostings = this.jobPostingDao.getAllByActiveIsTrue();
        List<GetAllJobPostingResponse> getAllJobPostingResponses = jobPostings.stream().map(jobPosting -> this.modelMapperService.forResponse().map(jobPosting, GetAllJobPostingResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(getAllJobPostingResponses, "Tum aktif is ilanlari listelendi");
    }

    @Override
    public DataResult<List<JobPosting>> getAllByActiveIsTrue(boolean ascending) {
        String message;
        Sort sort;
        if (ascending) {
            message = "Tum aktif is ilanlari ilk eklenen ustte sekilde listelendi";
            sort = Sort.by(Sort.Direction.ASC, "releaseDate");
        } else {
            message = "Tum aktif is ilanlari son eklenen ustte sekilde listelendi";
            sort = Sort.by(Sort.Direction.DESC, "releaseDate");
        }
        return new SuccessDataResult<>(this.jobPostingDao.getAllByActiveIsTrue(sort), message);
    }

    @Override
    public DataResult<List<JobPosting>> getAllByActiveAnAndCorporate_Id(int id) {
        return new SuccessDataResult<>(this.jobPostingDao.getAllByActiveAndCorporate_Id(true, id), "Firmaya ait tum ilanlar listelendi");
    }

    @Override
    public DataResult<GetByIdJobPosting> getById(int id) {
        var jobPosting = this.jobPostingDao.findById(id);
        if (jobPosting.isEmpty())
            return new ErrorDataResult<>("Is ilani bulunamadi");

        GetByIdJobPosting getByIdJobPosting = this.modelMapperService.forResponse().map(jobPosting, GetByIdJobPosting.class);
        return new SuccessDataResult<>(getByIdJobPosting, "Is ilani Id'sine gore getirildi");
    }

    @Override
    public Result setActive(JobPosting jobPosting, boolean active) {
        var jobPostingToUpdate = this.jobPostingDao.getById(jobPosting.getId());
        jobPostingToUpdate.setActive(active);
        jobPostingDao.save(jobPostingToUpdate);
        return new SuccessResult("Basariyla guncellendi");
    }
}
