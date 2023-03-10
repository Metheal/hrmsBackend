package kodlamaio.hrmsbackend.core.dataAccess;

import kodlamaio.hrmsbackend.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User getByEmail(String email);
    boolean existsByEmail(String email);
}
