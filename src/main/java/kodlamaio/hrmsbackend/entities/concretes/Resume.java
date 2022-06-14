package kodlamaio.hrmsbackend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "resumes")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "github_id")
    @Length(max = 50)
    private String githubId;

    @Column(name = "linkedin_id")
    @Length(max = 50)
    private String linkedinId;

    @Column(name = "cover_letter", nullable = false)
    @NotEmpty
    @NotNull
    @Length(max = 500)
    private String coverLetter;

    @ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private Applicant applicant;

    @JsonManagedReference
    @Valid
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<ApplicantSkill> applicantSkills;

    @JsonManagedReference
    @Valid
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @JsonManagedReference
    @Valid
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Education> educations;

    @JsonManagedReference
    @Valid
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<ForeignLanguage> foreignLanguages;
}
