package kodlamaio.hrmsbackend.business.rules;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantCheckService;
import kodlamaio.hrmsbackend.business.abstracts.ProfilePictureService;
import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.core.utilities.exceptions.BusinessException;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ApplicantDao;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ApplicantBusinessRules {
    private final ApplicantCheckService applicantCheckService;
    private final ApplicantDao applicantDao;
    private final ProfilePictureService profilePictureService;
    private final UserService userService;

    public void checkIfNationalIdIsUnique(String nationalId) {
        if (this.applicantDao.existsByNationalId(nationalId))
            throw new BusinessException("Bu kimlik numarasiyla kayit olusturulmus!");
    }

    public void checkIfCitizen(Applicant applicant) throws Exception {
        var result = this.applicantCheckService.checkIfCitizen(applicant);
        if (!result) {
            throw new BusinessException("Kimlik bilgileri gecerli degil!");
        }
    }

    public void checkIfFileProvided(Applicant applicant, MultipartFile file) throws IOException {
        if (file == null) {
            return;
        }
        this.profilePictureService.add(applicant.getProfilePicture(), file);
    }
}
