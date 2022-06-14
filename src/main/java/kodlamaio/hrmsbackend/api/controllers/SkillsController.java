package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.SkillService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {
    private SkillService skillService;

    @Autowired
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Skill>> getAll() {
        return this.skillService.getAll();
    }


    @GetMapping("/getById")
    public DataResult<Skill> getById(@RequestParam int id) {
        return this.skillService.getById(id);
    }


    @GetMapping("/getByName")
    public DataResult<Skill> getByName(@RequestParam String name) {
        return this.skillService.getByName(name);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Skill skill) {
        return this.skillService.add(skill);
    }
}
