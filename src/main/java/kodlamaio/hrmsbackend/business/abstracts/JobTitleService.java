package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.JobTitle;

import java.util.List;

public interface JobTitleService {
    List<JobTitle> getAll();
}
