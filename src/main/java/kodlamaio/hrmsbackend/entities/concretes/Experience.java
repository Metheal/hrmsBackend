package kodlamaio.hrmsbackend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "experiences")
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "workplace", nullable = false)
    @NotEmpty
    @NotNull
    private String workplace;

    @Column(name = "job_title", nullable = false)
    @NotEmpty
    @NotNull
    private String jobTitle;

    @Column(name = "start_date", nullable = false)
    @NotNull
    @DateTimeFormat
    private LocalDate startDate;

    @Column(name = "quit_date")
    @DateTimeFormat
    private LocalDate quitDate;

    @Column(name = "still_working", nullable = false)
    @NotNull
    private boolean stillWorking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "resume_id", referencedColumnName = "id", nullable = false)
    private Resume resume;

    public LocalDate getQuitDate() {
        // return quitDate null if applicant is still working
        return stillWorking ? null : this.quitDate;
    }
}
