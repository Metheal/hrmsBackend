package kodlamaio.hrmsbackend.adapters;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.hrmsbackend.business.abstracts.ImageUploadService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CloudinaryServiceAdapter implements ImageUploadService {
    @Override
    public DataResult<String> uploadImage(MultipartFile image) throws IOException {
        var cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "your",
                "api_key", "api",
                "api_secret", "key",
                "secure", true));

        File file = convert(image);
        var data = cloudinary.uploader().upload(file, ObjectUtils.emptyMap()).get("secure_url").toString();
        file.delete();
        return new SuccessDataResult<>(data);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
