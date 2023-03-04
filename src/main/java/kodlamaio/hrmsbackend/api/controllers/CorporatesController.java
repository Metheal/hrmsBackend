package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.CorporateService;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Corporate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/employers")
@AllArgsConstructor
public class CorporatesController {
    private CorporateService corporateService;

    @GetMapping
    public DataResult<List<Corporate>> getAll() {
        return this.corporateService.getAll();
    }

    @GetMapping("{id}")
    public DataResult<Corporate> getById(@PathVariable int id) {
        return this.corporateService.getById(id);
    }

    @GetMapping("getByUserId")
    public DataResult<Corporate> getByUserId(@RequestParam int id) {
        return this.corporateService.getByUserId(id);
    }

    @PostMapping("getByEmail")
    public DataResult<Corporate> getByUserEmail(@RequestBody User user) {
        return this.corporateService.getByUserEmail(user.getEmail());
    }

    @PostMapping
    public Result add(@Valid @RequestBody Corporate corporate) throws InterruptedException {
        return this.corporateService.add(corporate);
    }

    @PostMapping("/setActive")
    public Result setActive(@RequestBody Corporate corporate, @RequestParam boolean active) {
        return this.corporateService.setActive(corporate, active);
    }
}
