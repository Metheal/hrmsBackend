package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.JobTitleService;
import kodlamaio.hrmsbackend.business.requests.CreateJobTitleRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllJobTitleResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdJobTitleResponse;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobTitles")
@AllArgsConstructor
public class JobTitlesController {
    private final JobTitleService jobTitleService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<GetAllJobTitleResponse>> getAll() {
        return this.jobTitleService.getAll();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public DataResult<GetByIdJobTitleResponse> getById(@PathVariable int id) {
        return this.jobTitleService.getById(id);
    }

    @PostMapping
    @CrossOrigin
    public Result add(@RequestBody CreateJobTitleRequest createJobTitleRequest) {
        return this.jobTitleService.add(createJobTitleRequest);
    }
}
