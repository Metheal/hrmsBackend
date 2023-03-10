package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantService;
import kodlamaio.hrmsbackend.business.requests.CreateApplicantRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByEmailApplicantResponse;
import kodlamaio.hrmsbackend.business.responses.GetByUserIdApplicantResponse;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.ErrorDataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/applicants")
@AllArgsConstructor
public class ApplicantsController {
    private final ApplicantService applicantService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<GetAllApplicantResponse>> getAll() {
        return this.applicantService.getAll();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public DataResult<GetByIdApplicantResponse> getById(@PathVariable int id) {
        return this.applicantService.getById(id);
    }

    @GetMapping("getByUserId")
    @CrossOrigin
    public DataResult<GetByUserIdApplicantResponse> getByUserId(@RequestParam int id) {
        return this.applicantService.getByUserId(id);
    }

    @GetMapping("getByEmail")
    @CrossOrigin
    public DataResult<GetByEmailApplicantResponse> getByUserEmail(String email) {
        return this.applicantService.getByUserEmail(email);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public Result add(@Valid @RequestPart("createApplicantRequest") CreateApplicantRequest createApplicantRequest, @Nullable @RequestPart("file") MultipartFile file) throws Exception {
        return this.applicantService.add(createApplicantRequest, file);
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
