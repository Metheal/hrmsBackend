package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.EmployerService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Employer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
    private EmployerService employerService;

    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Employer employer) throws InterruptedException {
        return this.employerService.add(employer);
    }

    @PostMapping("/setActive")
    public Result setActive(@RequestBody Employer employer, @RequestParam boolean active) {
        return this.employerService.setActive(employer, active);
    }
}
