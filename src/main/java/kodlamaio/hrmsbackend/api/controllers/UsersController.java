package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.business.requests.CreateUserRequest;
import kodlamaio.hrmsbackend.business.responses.GetAllUserResponse;
import kodlamaio.hrmsbackend.business.responses.GetByIdUserResponse;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping
    @CrossOrigin
    public DataResult<List<GetAllUserResponse>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("{id}")
    @CrossOrigin
    public DataResult<GetByIdUserResponse> getById(@PathVariable int id) {
        return this.userService.getById(id);
    }

    @PostMapping("getByEmail")
    @CrossOrigin
    public DataResult<User> getByEmail(@RequestBody User user) {
        return this.userService.getByEmail(user.getEmail());
    }

    @PostMapping
    @CrossOrigin
    public Result add(@Valid @RequestBody CreateUserRequest createUserRequest) throws InterruptedException {
        return this.userService.add(createUserRequest);
    }
}
