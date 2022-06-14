package kodlamaio.hrmsbackend.entities.concretes;

import kodlamaio.hrmsbackend.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
public class Corporate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @Valid
    private User user;

    @Column(name="company_name", nullable = false)
    @NotEmpty
    @NotNull
    private String companyName;

    @Column(name = "website_url", nullable = false)
    @NotEmpty
    @NotNull
    @URL
    private String websiteUrl;

    @Column(name = "phone_number", nullable = false)
    @NotEmpty
    @NotNull
    @NumberFormat
    private String phoneNumber;

    @Column(name = "employees_count")
    private int employeesCount;
}
