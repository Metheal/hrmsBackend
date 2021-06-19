package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {
}
