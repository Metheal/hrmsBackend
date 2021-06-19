package kodlamaio.hrmsbackend.entities.concretes;

import kodlamaio.hrmsbackend.core.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "applicants")
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "national_id", nullable = false)
    private String nationalId;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
}
