package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.JobTitleService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobTitles")
public class JobTitlesController {

    private JobTitleService jobTitleService;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobTitle>> getAll() {
        return this.jobTitleService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobTitle jobTitle) {
        return this.jobTitleService.add(jobTitle);
    }
}
