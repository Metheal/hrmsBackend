package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.core.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}
