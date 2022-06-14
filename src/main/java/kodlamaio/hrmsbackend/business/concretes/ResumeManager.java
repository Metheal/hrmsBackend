package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.ResumeService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessDataResult;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessResult;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrmsbackend.entities.concretes.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {
    private ResumeDao resumeDao;

    @Autowired
    public ResumeManager(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<>(this.resumeDao.findAll());
    }

    @Override
    public DataResult<List<Resume>> getAllByApplicant_Id(int id) {
        return new SuccessDataResult<>(this.resumeDao.getAllByApplicant_Id(id));
    }

    @Override
    public DataResult<Resume> getById(int id) {
        return new SuccessDataResult<>(this.resumeDao.findById(id).get());
    }

    @Override
    public Result add(Resume resume) {
        this.resumeDao.save(resume);
        return new SuccessResult("Ozgecmis eklendi");
    }
}
