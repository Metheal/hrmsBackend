package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadService {
    DataResult<String> uploadImage(MultipartFile image) throws IOException;
}
