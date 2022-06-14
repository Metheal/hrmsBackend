package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.ApplicantSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantSkillDao extends JpaRepository<ApplicantSkill, Integer> {
}
