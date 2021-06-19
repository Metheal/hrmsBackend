package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.CityService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    public DataResult<List<City>> getAll() {
        return this.cityService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<City> getById(@RequestParam int id) {
        return this.cityService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody City city) {
        return this.cityService.add(city);
    }
}
