package kodlamaio.hrmsbackend.business.rules;

import kodlamaio.hrmsbackend.core.utilities.exceptions.BusinessException;
import kodlamaio.hrmsbackend.dataAccess.abstracts.JobTitleDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobTitleBusinessRules {
    private final JobTitleDao jobTitleDao;

    public void checkIfJobTitleExists(String name) {
        if (this.jobTitleDao.existsByName(name))
            throw new BusinessException("Bu isme sahip is pozisyonu mevcut!");
    }
}
