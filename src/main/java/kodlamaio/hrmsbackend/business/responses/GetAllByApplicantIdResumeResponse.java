package kodlamaio.hrmsbackend.business.responses;

import kodlamaio.hrmsbackend.entities.concretes.Education;
import kodlamaio.hrmsbackend.entities.concretes.Experience;
import kodlamaio.hrmsbackend.entities.concretes.ForeignLanguage;
import kodlamaio.hrmsbackend.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllByApplicantIdResumeResponse {
    private int id;
    private String githubId;
    private String linkedinId;
    private String coverLetter;
    private List<Skill> applicantSkills;
    private List<Experience> experiences;
    private List<Education> educations;
    private List<ForeignLanguage> foreignLanguages;
}
