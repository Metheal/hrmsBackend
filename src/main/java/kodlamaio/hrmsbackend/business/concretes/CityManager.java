package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.CityService;
import kodlamaio.hrmsbackend.core.utilities.results.*;
import kodlamaio.hrmsbackend.dataAccess.abstracts.CityDao;
import kodlamaio.hrmsbackend.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<City> getById(int id) {
        return new SuccessDataResult<>(this.cityDao.getById(id));
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<>(this.cityDao.findAll());
    }

    @Override
    public Result add(City city) {
        var checkIfNameEmpty = checkIfNameEmpty(city);
        if (!checkIfNameEmpty.isSuccess()) {
            return new ErrorResult(checkIfNameEmpty.getMessage());
        }
        this.cityDao.save(city);
        return new SuccessResult();
    }

    private Result checkIfNameEmpty(City city) {
        var result = city.getName();
        if (result.isEmpty()) {
            return new ErrorResult("Sehir ismi bos birakilamaz");
        }
        return new SuccessResult();
    }
}
