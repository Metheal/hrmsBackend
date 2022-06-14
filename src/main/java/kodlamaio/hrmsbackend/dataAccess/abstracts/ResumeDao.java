package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeDao extends JpaRepository<Resume, Integer> {
    List<Resume> getAllByApplicant_Id(int id);
}
