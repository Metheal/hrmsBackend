package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
    Employer getByPhoneNumber(String phoneNumber);
    Employer getByWebsiteUrl(String websiteUrl);
    Employer getByUser_Id(int id);
    Employer getByUser_Email(String email);
}
