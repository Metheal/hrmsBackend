package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.CorporateService;
import kodlamaio.hrmsbackend.business.requests.CreateCorporateRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllCorporateResponse;
import kodlamaio.hrmsbackend.business.responses.GetByEmailCorporateResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdCorporateResponse;
import kodlamaio.hrmsbackend.business.responses.GetByUserIdCorporateResponse;
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
    private final CorporateService corporateService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<GetAllCorporateResponse>> getAll() {
        return this.corporateService.getAll();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public DataResult<GetByIdCorporateResponse> getById(@PathVariable int id) {
        return this.corporateService.getById(id);
    }

    @GetMapping("getByUserId")
    @CrossOrigin
    public DataResult<GetByUserIdCorporateResponse> getByUserId(@RequestParam int id) {
        return this.corporateService.getByUserId(id);
    }

    @GetMapping("getByEmail")
    @CrossOrigin
    public DataResult<GetByEmailCorporateResponse> getByUserEmail(@RequestParam String email) {
        return this.corporateService.getByUserEmail(email);
    }

    @PostMapping
    @CrossOrigin
    public Result add(@Valid @RequestBody CreateCorporateRequest createCorporateRequest) throws InterruptedException {
        return this.corporateService.add(createCorporateRequest);
    }

    @PostMapping("setActive")
    @CrossOrigin
    public Result setActive(@RequestBody Corporate corporate, @RequestParam boolean active) {
        return this.corporateService.setActive(corporate, active);
    }
}
