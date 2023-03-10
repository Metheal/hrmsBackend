package kodlamaio.hrmsbackend.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllJobPostingResponse {
    private int id;
    private String jobTitleName;
    private String cityName;
    private int minSalary;
    private int maxSalary;
    private String companyName;
    private LocalDate releaseDate;
    private LocalDate deadline;
}
