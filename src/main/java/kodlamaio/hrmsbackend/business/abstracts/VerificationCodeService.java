package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.business.requests.VerifyApplicantRequest;
import kodlamaio.hrmsbackend.business.requests.VerifyCorporateRequest;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;

public interface VerificationCodeService {
    DataResult<String> createCode(User user);

    Result verifyApplicant(VerifyApplicantRequest verifyApplicantRequest, String code);

    Result verifyCorporate(VerifyCorporateRequest verifyCorporateRequest, String code);

    Result deleteCode(String code);
}
