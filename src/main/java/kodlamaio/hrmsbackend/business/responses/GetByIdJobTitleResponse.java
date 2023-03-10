package kodlamaio.hrmsbackend.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdJobTitleResponse {
    private int id;
    private String name;
}
