package cn.andiedie.entities.repositories;

import cn.andiedie.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    public User find(long id) {
        return users.get(id);
    }

    public List<User> all() {
        return new ArrayList<User>(users.values());
    }

    public boolean add(User u) {
        if (users.containsValue(u))
            return false;
        users.put(u.getId(), u);
        return true;
    }

    public User delete(Long id) {
        return users.remove(id);
    }

    public User update(long id, User u) {
        return users.replace(id, u);
    }
}
