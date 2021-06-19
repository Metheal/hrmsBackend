package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<City> getById(int id);

    DataResult<List<City>> getAll();

    Result add(City city);
}
