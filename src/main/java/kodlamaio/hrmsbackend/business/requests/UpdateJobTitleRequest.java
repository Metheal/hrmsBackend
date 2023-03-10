package kodlamaio.hrmsbackend.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobTitleRequest {
    @NotNull
    private Integer id;
    @NotNull
    @NotEmpty
    private String name;
}
