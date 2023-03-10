package kodlamaio.hrmsbackend.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllResumeResponse {
    private int id;
    private String githubId;
    private String linkedinId;
    private String coverLetter;
}
