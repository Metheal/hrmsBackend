package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantService;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.ErrorDataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantsController {
    private ApplicantService applicantService;

    @Autowired
    public ApplicantsController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Applicant>> getAll() {
        return this.applicantService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Applicant> getById(@RequestParam int id) {
        return this.applicantService.getById(id);
    }

    @GetMapping("/getByUserId")
    public DataResult<Applicant> getByUserId(@RequestParam int id) {
        return this.applicantService.getByUserId(id);
    }

    @PostMapping("/getByEmail")
    public DataResult<Applicant> getByUserEmail(@RequestBody User user) {
        return this.applicantService.getByUserEmail(user.getEmail());
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Result add(@Valid @RequestPart("applicant") Applicant applicant, @Nullable @RequestPart("file") MultipartFile file) throws Exception {
        return this.applicantService.add(applicant, file);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ErrorDataResult<>(validationErrors, "Dogrulama hatalari");
    }
}
