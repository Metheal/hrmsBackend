package kodlamaio.hrmsbackend.entities.concretes;

import kodlamaio.hrmsbackend.core.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name="company_name", nullable = false)
    private String companyName;

    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "employees_count")
    private int employeesCount;
}
