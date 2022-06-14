package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateDao extends JpaRepository<Corporate, Integer> {
    Corporate getByPhoneNumber(String phoneNumber);
    Corporate getByWebsiteUrl(String websiteUrl);
    Corporate getByUser_Id(int id);
    Corporate getByUser_Email(String email);
}
