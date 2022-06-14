package kodlamaio.hrmsbackend.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profile_pictures")
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "applicant_id", referencedColumnName = "id")
    private Applicant applicant;
}
