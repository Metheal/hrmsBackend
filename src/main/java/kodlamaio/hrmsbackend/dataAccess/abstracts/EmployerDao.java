package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
    Employer getEmployerByPhoneNumber(String phoneNumber);
    Employer getEmployerByWebsiteUrl(String websiteUrl);
}
