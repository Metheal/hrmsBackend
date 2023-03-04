package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.ResumeService;
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
    private ResumeService resumeService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<Resume>> getAll() {
        return this.resumeService.getAll();
    }

    @GetMapping("getAllByApplicant_Id")
    @CrossOrigin
    DataResult<List<Resume>> getAllByApplicant_Id(@RequestParam int id) {
        return this.resumeService.getAllByApplicant_Id(id);
    }

    @GetMapping("{id}")
    DataResult<Resume> getById(@PathVariable int id) {
        return this.resumeService.getById(id);
    }

    @PostMapping
    Result add(@Valid @RequestBody Resume resume) {
        return this.resumeService.add(resume);
    }
}
