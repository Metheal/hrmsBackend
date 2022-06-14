package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.ProfilePicture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfilePictureService {
    Result add(ProfilePicture profilePicture, MultipartFile file) throws IOException;
}
