package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureDao extends JpaRepository<ProfilePicture, Integer> {
}
