package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.JobPostingService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {
    private JobPostingDao jobPostingDao;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao) {
        this.jobPostingDao = jobPostingDao;
    }

    @Override
    public Result add(JobPosting jobPosting) {
        var checkIfJobTitleEmpty = checkIfJobTitleEmpty(jobPosting);
        if (!checkIfJobTitleEmpty.isSuccess()) {
            return new ErrorResult(checkIfJobTitleEmpty.getMessage());
        }
        var checkIfDescriptionEmpty = checkIfDescriptionEmpty(jobPosting);
        if (!checkIfDescriptionEmpty.isSuccess()) {
            return new ErrorResult(checkIfDescriptionEmpty.getMessage());
        }
        var checkIfCityEmpty = checkIfCityEmpty(jobPosting);
        if (!checkIfCityEmpty.isSuccess()) {
            return new ErrorResult(checkIfCityEmpty.getMessage());
        }
        var checkIfOpenPositionsEmpty = checkIfOpenPositionsEmpty(jobPosting);
        if (!checkIfOpenPositionsEmpty.isSuccess()) {
            return new ErrorResult(checkIfCityEmpty.getMessage());
        }
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
    public DataResult<List<JobPosting>> getAllByActiveIsTrue() {
        return new SuccessDataResult<>(this.jobPostingDao.getAllByActiveIsTrue(), "Tum aktif is ilanlari listelendi");
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
    public DataResult<JobPosting> getById(int id) {
        return new SuccessDataResult<>(this.jobPostingDao.findById(id).get());
    }

    @Override
    public Result setActive(JobPosting jobPosting, boolean active) {
        var jobPostingToUpdate = this.jobPostingDao.getById(jobPosting.getId());
        jobPostingToUpdate.setActive(active);
        jobPostingDao.save(jobPostingToUpdate);
        return new SuccessResult("Basariyla guncellendi");
    }

    private Result checkIfJobTitleEmpty(JobPosting jobPosting) {
        var result = jobPosting.getJobTitle();
        if (result == null) {
            return new ErrorResult("Bir is pozisyonu secmeniz gerekiyor");
        }
        return new SuccessResult();
    }

    private Result checkIfDescriptionEmpty(JobPosting jobPosting) {
        var result = jobPosting.getDescription();
        if (result.isEmpty()) {
            return new ErrorResult("Bir aciklama yazmaniz gerekiyor");
        }
        return new SuccessResult();
    }

    private Result checkIfCityEmpty(JobPosting jobPosting) {
        var result = jobPosting.getCity();
        if (result == null) {
            return new ErrorResult("Bir sehir secmeniz gerekiyor");
        }
        return new SuccessResult();
    }

    private Result checkIfOpenPositionsEmpty(JobPosting jobPosting) {
        var result = jobPosting.getOpenPositions();
        if (result <= 0) {
            return new ErrorResult("Acik pozisyon sayisini girmeniz gerekiyor");
        }
        return new SuccessResult();
    }
}
