package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantDao extends JpaRepository<Applicant, Integer> {
    Applicant getByUser_Id(int id);
    Applicant findByUser_Id(int id);
    Applicant getByUser_Email(String email);
    boolean existsByNationalId(String nationalId);
}
