package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.ImageUploadService;
import kodlamaio.hrmsbackend.business.abstracts.ProfilePictureService;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessResult;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ProfilePictureDao;
import kodlamaio.hrmsbackend.entities.concretes.ProfilePicture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProfilePictureManager implements ProfilePictureService {
    private final ProfilePictureDao profilePictureDao;
    private final ImageUploadService imageUploadService;

    @Override
    public Result add(ProfilePicture profilePicture, MultipartFile file) throws IOException {
        profilePicture.setUrl(this.imageUploadService.uploadImage(file).getMessage());
        this.profilePictureDao.save(profilePicture);
        return new SuccessResult("Profil resmi eklendi");
    }
}
