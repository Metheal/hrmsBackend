package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.JobTitleService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrmsbackend.entities.concretes.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleManager implements JobTitleService {

    private JobTitleDao jobTitleDao;

    @Autowired
    public JobTitleManager(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    @Override
    public DataResult<List<JobTitle>> getAll() {
        return new SuccessDataResult<>(this.jobTitleDao.findAll(), "Is pozisyonlari listelendi");
    }

    @Override
    public Result add(JobTitle jobTitle) {
        var result = checkIfJobTitleExists(jobTitle);
        if (!result.isSuccess()) {
            return new ErrorResult(result.getMessage());
        }
        this.jobTitleDao.save(jobTitle);
        return new SuccessResult("Is pozisyonu eklendi " + jobTitle.getName());
    }

    private Result checkIfJobTitleExists(JobTitle jobTitle) {
        var result = this.jobTitleDao.getByName(jobTitle.getName());
        if (result != null) {
            return new ErrorResult("Bu isme sahip is pozisyonu mevcut");
        }
        return new SuccessResult();
    }
}
