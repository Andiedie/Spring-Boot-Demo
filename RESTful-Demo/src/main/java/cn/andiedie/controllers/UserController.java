package cn.andiedie.controllers;

import cn.andiedie.entities.User;
import cn.andiedie.exceptions.UserExistException;
import cn.andiedie.exceptions.UserNotFindException;
import cn.andiedie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> all() {
        return userService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody User u) {
        boolean ok = userService.add(u);
        if (!ok) throw new UserExistException();
    }

    @GetMapping("/{id}")
    public User find(@PathVariable("id") long id) {
        User user = userService.find(id);
        if (user == null) throw new UserNotFindException();
        return user;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable("id") long id, @RequestBody User u) {
        User pre = userService.update(id, u);
        if (pre == null) throw new UserNotFindException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        User pre = userService.delete(id);
        if (pre == null) throw new UserNotFindException();
    }
}
