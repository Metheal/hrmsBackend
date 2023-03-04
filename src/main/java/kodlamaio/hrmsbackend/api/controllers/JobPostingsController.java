package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.JobPostingService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostings")
@AllArgsConstructor
public class JobPostingsController {
    private JobPostingService jobPostingService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<JobPosting>> getAll() {
        return this.jobPostingService.getAllByActiveIsTrue();
    }

    @GetMapping("/getAllSortedByDate")
    public DataResult<List<JobPosting>> getAllByActive(boolean ascending) {
        return this.jobPostingService.getAllByActiveIsTrue(ascending);
    }

    @GetMapping("/getAllByEmployerId")
    public DataResult<List<JobPosting>> getAllByEmployerId(@RequestParam int id) {
        return this.jobPostingService.getAllByActiveAnAndCorporate_Id(id);
    }

    @GetMapping("{id}")
    public DataResult<JobPosting> getById(@PathVariable int id) {
        return this.jobPostingService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody JobPosting jobPosting) {
        return this.jobPostingService.add(jobPosting);
    }

    @PostMapping("/setActive")
    public Result setActive(@RequestBody JobPosting jobPosting, @RequestParam boolean active) {
        return this.jobPostingService.setActive(jobPosting, active);
    }
}
