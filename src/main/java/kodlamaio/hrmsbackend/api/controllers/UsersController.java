package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.core.entities.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public DataResult<List<User>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("{id}")
    public DataResult<User> getById(@PathVariable int id) {
        return this.userService.getById(id);
    }

    @PostMapping("getByEmail")
    public DataResult<User> getByEmail(@RequestBody User user) {
        return this.userService.getByEmail(user.getEmail());
    }

    @PostMapping
    public Result add(@Valid @RequestBody User user) throws InterruptedException {
        return this.userService.add(user);
    }
}
