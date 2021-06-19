package kodlamaio.hrmsbackend.api.controllers;

import kodlamaio.hrmsbackend.business.abstracts.UserService;
import kodlamaio.hrmsbackend.core.entities.concretes.User;
import kodlamaio.hrmsbackend.core.utilities.results.DataResult;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public DataResult<List<User>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<User> getById(@RequestParam int id) {
        return this.userService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) throws InterruptedException {
        return this.userService.add(user);
    }
}
