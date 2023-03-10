package kodlamaio.hrmsbackend.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobPostingRequest {
    @NotNull
    private Integer jobTitleId;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private Integer cityId;

    @NotNull
    private Integer minSalary;

    @NotNull
    private Integer maxSalary;

    @NotNull
    private Integer openPositions;

    @NotNull
    @NotEmpty
    @DateTimeFormat
    private LocalDate deadline;

    @NotNull
    private Integer corporateId;
}
