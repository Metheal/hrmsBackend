package kodlamaio.hrmsbackend.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByUserIdCorporateResponse {
    private int id;
    private String companyName;
    private String websiteUrl;
    private String phoneNumber;
    private int employeesCount;
    private String email;
}
