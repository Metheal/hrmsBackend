package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import kodlamaio.hrmsbackend.business.requests.VerifyApplicantRequest;
import kodlamaio.hrmsbackend.business.requests.VerifyCorporateRequest;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/verification")
@AllArgsConstructor
public class EmailVerificationController {
    private final VerificationCodeService verificationCodeService;

    @PostMapping("verifyApplicant")
    @CrossOrigin
    public Result verifyApplicant(@RequestBody VerifyApplicantRequest verifyApplicantRequest, @RequestParam String code) {
        return this.verificationCodeService.verifyApplicant(verifyApplicantRequest, code);
    }

    @PostMapping("verifyEmployer")
    @CrossOrigin
    public Result verifyEmployer(@RequestBody VerifyCorporateRequest verifyCorporateRequest, @RequestParam String code) {
        return this.verificationCodeService.verifyCorporate(verifyCorporateRequest, code);
    }
}
