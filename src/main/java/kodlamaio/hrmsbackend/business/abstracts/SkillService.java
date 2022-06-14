package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Skill;

import java.util.List;

public interface SkillService {
    DataResult<List<Skill>> getAll();

    DataResult<Skill> getById(int id);

    DataResult<Skill> getByName(String name);

    Result add(Skill skill);
}
