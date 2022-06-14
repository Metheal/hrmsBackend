package kodlamaio.hrmsbackend.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "email", nullable = false)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @NotNull
    private String password;

    @Transient
    @NotEmpty
    @NotNull
    private String passwordConfirmation;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;
}
