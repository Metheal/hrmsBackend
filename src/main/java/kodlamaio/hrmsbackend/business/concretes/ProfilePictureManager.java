package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.ImageUploadService;
import kodlamaio.hrmsbackend.business.abstracts.ProfilePictureService;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessResult;
import kodlamaio.hrmsbackend.dataAccess.abstracts.ProfilePictureDao;
import kodlamaio.hrmsbackend.entities.concretes.ProfilePicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfilePictureManager implements ProfilePictureService {
    private ProfilePictureDao profilePictureDao;
    private ImageUploadService imageUploadService;

    @Autowired
    public ProfilePictureManager(ProfilePictureDao profilePictureDao, ImageUploadService imageUploadService) {
        this.profilePictureDao = profilePictureDao;
        this.imageUploadService = imageUploadService;
    }

    @Override
    public Result add(ProfilePicture profilePicture, MultipartFile file) throws IOException {
        profilePicture.setUrl(this.imageUploadService.uploadImage(file).getMessage());
        this.profilePictureDao.save(profilePicture);
        return new SuccessResult("Profil resmi eklendi");
    }
}
