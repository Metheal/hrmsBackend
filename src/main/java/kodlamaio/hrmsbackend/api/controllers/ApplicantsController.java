package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantsController {
    private ApplicantService applicantService;

    @Autowired
    public ApplicantsController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Applicant>> getAll() {
        return this.applicantService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Applicant applicant) throws Exception {
        return this.applicantService.add(applicant);
    }
}
