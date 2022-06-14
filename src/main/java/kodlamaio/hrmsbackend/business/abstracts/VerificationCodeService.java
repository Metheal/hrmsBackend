package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;

public interface VerificationCodeService {
    DataResult<String> createCode(User user);

    Result verifyApplicant(User user, String code);

    Result verifyCorporate(User user, String code);

    Result deleteCode(String code);
}
