package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.ResumeService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {
    private ResumeService resumeService;

    @Autowired
    public ResumesController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Resume>> getAll() {
        return this.resumeService.getAll();
    }

    @GetMapping("/getAllByApplicant_Id")
    DataResult<List<Resume>> getAllByApplicant_Id(@RequestParam int id) {
        return this.resumeService.getAllByApplicant_Id(id);
    }

    @GetMapping("/getById")
    DataResult<Resume> getById(@RequestParam int id) {
        return this.resumeService.getById(id);
    }

    @PostMapping("/add")
    Result add(@Valid @RequestBody Resume resume) {
        return this.resumeService.add(resume);
    }
}
