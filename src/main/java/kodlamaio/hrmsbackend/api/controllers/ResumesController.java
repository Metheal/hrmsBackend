package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.ResumeService;
import kodlamaio.hrmsbackend.business.responses.GetAllByApplicantIdResumeResponse;
import kodlamaio.hrmsbackend.business.responses.GetAllResumeResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdResumeResponse;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Resume;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/resumes")
@AllArgsConstructor
public class ResumesController {
    private final ResumeService resumeService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<GetAllResumeResponse>> getAll() {
        return this.resumeService.getAll();
    }

    @GetMapping("getAllByApplicant_Id")
    @CrossOrigin
    DataResult<List<GetAllByApplicantIdResumeResponse>> getAllByApplicant_Id(@RequestParam int id) {
        return this.resumeService.getAllByApplicant_Id(id);
    }

    @GetMapping("{id}")
    @CrossOrigin
    DataResult<GetByIdResumeResponse> getById(@PathVariable int id) {
        return this.resumeService.getById(id);
    }

    @PostMapping
    @CrossOrigin
    Result add(@Valid @RequestBody Resume resume) {
        return this.resumeService.add(resume);
    }
}
