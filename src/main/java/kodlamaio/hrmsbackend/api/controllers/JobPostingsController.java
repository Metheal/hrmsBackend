package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.JobPostingService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostings")
public class JobPostingsController {

    private JobPostingService jobPostingService;

    @Autowired
    public JobPostingsController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobPosting>> getAll() {
        return this.jobPostingService.getAllByActiveIsTrue();
    }

    @GetMapping("/getAllSortedByDate")
    public DataResult<List<JobPosting>> getAllByActive(boolean ascending) {
        return this.jobPostingService.getAllByActiveIsTrue(ascending);
    }

    @GetMapping("/getAllByEmployerId")
    public DataResult<List<JobPosting>> getAllByEmployerId(@RequestParam int id) {
        return this.jobPostingService.getAllByActiveAnAndEmployer_Id(id);
    }

    @GetMapping("/getById")
    public DataResult<JobPosting> getById(@RequestParam int id) {
        return this.jobPostingService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPosting jobPosting) {
        return this.jobPostingService.add(jobPosting);
    }

    @PostMapping("/setActive")
    public Result setActive(@RequestBody JobPosting jobPosting, @RequestParam boolean active) {
        return this.jobPostingService.setActive(jobPosting, active);
    }
}
