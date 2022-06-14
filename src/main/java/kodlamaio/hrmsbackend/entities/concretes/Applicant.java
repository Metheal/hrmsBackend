package kodlamaio.hrmsbackend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kodlamaio.hrmsbackend.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "applicants")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resumes"})
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @Valid
    private User user;

    @Column(name = "first_name", nullable = false)
    @NotEmpty
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty
    @NotNull
    private String lastName;

    @Column(name = "national_id", nullable = false, unique = true)
    @NotEmpty
    @NotNull
    @Length(min = 11, max = 11)
    private String nationalId;

    @Column(name = "date_of_birth", nullable = false)
    @NotNull
    @DateTimeFormat
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Resume> resumes;

    @JsonManagedReference
    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    private ProfilePicture profilePicture;
}
