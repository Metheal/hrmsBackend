package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.ResumeService;
import kodlamaio.hrmsbackend.business.responses.GetAllByApplicantIdResumeResponse;
import kodlamaio.hrmsbackend.business.responses.GetAllResumeResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdResumeResponse;
import kodlamaio.hrmsbackend.core.utilities.mappers.ModelMapperService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrmsbackend.entities.concretes.Resume;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResumeManager implements ResumeService {
    private final ResumeDao resumeDao;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllResumeResponse>> getAll() {
        List<Resume> resumes = this.resumeDao.findAll();
        List<GetAllResumeResponse> getAllResumeResponses = resumes.stream()
                .map(resume -> this.modelMapperService.forResponse()
                        .map(resume, GetAllResumeResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(getAllResumeResponses, "Tum ozgecmisler listelendi");
    }

    @Override
    public DataResult<List<GetAllByApplicantIdResumeResponse>> getAllByApplicant_Id(int id) {
        List<Resume> resumes = this.resumeDao.getAllByApplicant_Id(id);
        List<GetAllByApplicantIdResumeResponse> getAllByApplicantIdResumeResponses = resumes.stream()
                .map(resume -> this.modelMapperService.forResponse()
                        .map(resume, GetAllByApplicantIdResumeResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(getAllByApplicantIdResumeResponses, "Ozgecmisler Aday Id'sine gore getirildi");
    }

    @Override
    public DataResult<GetByIdResumeResponse> getById(int id) {
        var resume = this.resumeDao.findById(id);
        if (resume.isEmpty())
            return new ErrorDataResult<>("Ozgecmis bulunamadi");
        GetByIdResumeResponse getByIdResumeResponse
                = this.modelMapperService.forResponse().map(resume.get(), GetByIdResumeResponse.class);
        return new SuccessDataResult<>(getByIdResumeResponse, "Ozgecmis Id'sine gore getirildi");
    }

    @Override
    public Result add(Resume resume) {
        this.resumeDao.save(resume);
        return new SuccessResult("Ozgecmis eklendi");
    }
}
