package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.CityService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.City;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@AllArgsConstructor
public class CitiesController {
    private final CityService cityService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<City>> getAll() {
        return this.cityService.getAll();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public DataResult<City> getById(@PathVariable int id) {
        return this.cityService.getById(id);
    }

    @PostMapping
    @CrossOrigin
    public Result add(@RequestBody City city) {
        return this.cityService.add(city);
    }
}
