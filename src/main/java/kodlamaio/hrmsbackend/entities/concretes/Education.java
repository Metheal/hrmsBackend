package kodlamaio.hrmsbackend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "educations")
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "university_name", nullable = false)
    @NotEmpty
    @NotNull
    private String universityName;

    @Column(name = "department", nullable = false)
    @NotEmpty
    @NotNull
    private String department;

    @Column(name = "register_date", nullable = false)
    @NotNull
    private LocalDate registerDate;

    @Column(name = "graduate_date")
    private LocalDate graduateDate;

    @Column(name = "graduated", nullable = false)
    @NotNull
    private boolean stillAttending;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "resume_id", referencedColumnName = "id", nullable = false)
    private Resume resume;

    public LocalDate getGraduateDate() {
        // return graduateDate null if applicant is still a student
        return stillAttending ? null : this.graduateDate;
    }
}
