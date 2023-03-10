package kodlamaio.hrmsbackend.business.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateRequest {
    @NotEmpty
    @NotNull
    private String companyName;
    @NotEmpty
    @NotNull
    private String websiteUrl;
    @NotEmpty
    @NotNull
    private String phoneNumber;
    @NotNull
    @Range(min = 1)
    private Integer employeesCount;
    @NotEmpty
    @NotNull
    private String email;
    @NotEmpty
    @NotNull
    private String password;
    @NotEmpty
    @NotNull
    private String passwordConfirmation;

    @JsonIgnore
    private int userId;
}
