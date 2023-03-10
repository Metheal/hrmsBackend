package kodlamaio.hrmsbackend.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdApplicantResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private LocalDate dateOfBirth;
    private int userId;
}
