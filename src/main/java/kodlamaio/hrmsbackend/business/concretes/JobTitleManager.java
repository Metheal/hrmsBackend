package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.JobTitleService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessDataResult;
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
}
