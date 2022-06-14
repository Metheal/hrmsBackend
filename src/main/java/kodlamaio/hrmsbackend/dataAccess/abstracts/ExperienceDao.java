package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceDao extends JpaRepository<Experience, Integer> {
}
