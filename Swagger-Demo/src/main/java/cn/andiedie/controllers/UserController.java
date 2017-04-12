package cn.andiedie.controllers;

import cn.andiedie.entities.User;
import cn.andiedie.exceptions.UserExistException;
import cn.andiedie.exceptions.UserNotFindException;
import cn.andiedie.services.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 没有@ApiOperation注解, 依旧会在Swagger中显示, 使用函数名作为描述
    @GetMapping
    public List<User> all() {
        return userService.all();
    }

    // 为API添加描述
    @ApiOperation("创建用户")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody User u) {
        boolean ok = userService.add(u);
        if (!ok) throw new UserExistException();
    }

    // 为API添加描述和详情, 还添加了指定参数的描述
    @ApiOperation(value = "获取用户详细信息", notes = "根据ID获取用户的详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @GetMapping("/{id}")
    public User find(@PathVariable("id") long id) {
        User user = userService.find(id);
        if (user == null) throw new UserNotFindException();
        return user;
    }

    // 为多个参数添加描述
    @ApiOperation("更新用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "用户ID", required = true),
        @ApiImplicitParam(name = "u", value = "新的用户信息",required = true)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable("id") long id, @RequestBody User u) {
        User pre = userService.update(id, u);
        if (pre == null) throw new UserNotFindException();
    }

    // 使用@ApiIgnore后Swagger中不会显示这个API
    @ApiIgnore
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        User pre = userService.delete(id);
        if (pre == null) throw new UserNotFindException();
    }
}
