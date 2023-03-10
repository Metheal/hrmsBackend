package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.SkillService;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessDataResult;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessResult;
import kodlamaio.hrmsbackend.dataAccess.abstracts.SkillDao;
import kodlamaio.hrmsbackend.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillManager implements SkillService {
    private SkillDao skillDao;

    @Override
    public DataResult<List<Skill>> getAll() {
        return new SuccessDataResult<>(this.skillDao.findAll());
    }

    @Override
    public DataResult<Skill> getById(int id) {
        return new SuccessDataResult<>(this.skillDao.findById(id).get());
    }

    @Override
    public DataResult<Skill> getByName(String name) {
        return new SuccessDataResult<>(this.skillDao.getByName(name));
    }

    @Override
    public Result add(Skill skill) {
        this.skillDao.save(skill);
        return new SuccessResult("Yetenek eklendi");
    }
}
