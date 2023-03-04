package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.business.abstracts.VerificationCodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/verification")
@AllArgsConstructor
public class EmailVerificationController {
    private VerificationCodeService verificationCodeService;

    @PostMapping("verifyApplicant")
    public Result verifyApplicant(@RequestBody User user, @RequestParam String code) {
        return this.verificationCodeService.verifyApplicant(user, code);
    }

    @PostMapping("verifyEmployer")
    public Result verifyEmployer(@RequestBody User user, @RequestParam String code) {
        return this.verificationCodeService.verifyCorporate(user, code);
    }
}
