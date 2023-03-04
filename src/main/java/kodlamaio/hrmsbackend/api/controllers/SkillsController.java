package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.SkillService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/skills")
@AllArgsConstructor
public class SkillsController {
    private SkillService skillService;

    @GetMapping
    public DataResult<List<Skill>> getAll() {
        return this.skillService.getAll();
    }


    @GetMapping("{id}")
    public DataResult<Skill> getById(@PathVariable int id) {
        return this.skillService.getById(id);
    }


    @GetMapping("getByName")
    public DataResult<Skill> getByName(@RequestParam String name) {
        return this.skillService.getByName(name);
    }


    @PostMapping
    public Result add(@RequestBody Skill skill) {
        return this.skillService.add(skill);
    }
}
