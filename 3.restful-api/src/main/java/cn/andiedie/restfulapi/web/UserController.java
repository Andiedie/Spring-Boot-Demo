package cn.andiedie.restfulapi.web;

import cn.andiedie.restfulapi.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    // @ModelAttribute 可以处理form-data、x-www-form-urlencoded和query
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        System.out.println(user.getId());
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id) {
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id) {
        users.remove(id);
        return "success";
    }
}
