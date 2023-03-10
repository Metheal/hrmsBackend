package kodlamaio.hrmsbackend.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    @NotNull
    private Integer id;

    @NotEmpty
    @NotNull
    private String name;
}
