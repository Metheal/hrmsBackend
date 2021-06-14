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
    @Column(name="id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name="company_name")
    private String companyName;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "employees_count")
    private int employeesCount;
}
