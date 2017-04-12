package cn.andiedie.services;

import cn.andiedie.entities.User;
import cn.andiedie.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(long id) {
        return userRepository.find(id);
    }

    public List<User> all() {
        return userRepository.all();
    }

    public boolean add(User u) {
        return userRepository.add(u);
    }

    public User update(long id, User u) {
        return userRepository.update(id, u);
    }

    public User delete(long id) {
        return userRepository.delete(id);
    }
}
