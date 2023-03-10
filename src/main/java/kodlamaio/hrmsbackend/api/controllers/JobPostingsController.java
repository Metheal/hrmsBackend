package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.JobPostingService;
import kodlamaio.hrmsbackend.business.requests.CreateJobPostingRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllJobPostingResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdJobPosting;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.JobPosting;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobPostings")
@AllArgsConstructor
public class JobPostingsController {
    private final JobPostingService jobPostingService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<GetAllJobPostingResponse>> getAll() {
        return this.jobPostingService.getAllByActiveIsTrue();
    }

    @GetMapping("getAllSortedByDate")
    @CrossOrigin
    public DataResult<List<JobPosting>> getAllByActive(boolean ascending) {
        return this.jobPostingService.getAllByActiveIsTrue(ascending);
    }

    @GetMapping("getAllByEmployerId")
    @CrossOrigin
    public DataResult<List<JobPosting>> getAllByEmployerId(@RequestParam int id) {
        return this.jobPostingService.getAllByActiveAnAndCorporate_Id(id);
    }

    @GetMapping("{id}")
    @CrossOrigin
    public DataResult<GetByIdJobPosting> getById(@PathVariable int id) {
        return this.jobPostingService.getById(id);
    }

    @PostMapping
    @CrossOrigin
    public Result add(@RequestBody CreateJobPostingRequest createJobPostingRequest) {
        return this.jobPostingService.add(createJobPostingRequest);
    }

    @PostMapping("setActive")
    @CrossOrigin
    public Result setActive(@RequestBody JobPosting jobPosting, @RequestParam boolean active) {
        return this.jobPostingService.setActive(jobPosting, active);
    }
}
