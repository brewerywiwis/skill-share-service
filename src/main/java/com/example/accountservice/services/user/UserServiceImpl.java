package com.example.accountservice.services.user;

import com.example.accountservice.entities.User;
import com.example.accountservice.repositories.UserRepository;
import com.example.accountservice.types.requests.EditUserRequest;
import com.example.accountservice.types.requests.SearchUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User cannot be founded"));
    }

    @Override
    public User getUserByCriteria(SearchUserRequest criteria) {
        return null;
    }

    @Override
    public List<User> getUserInformationById(List<UUID> uuids){
        return userRepository.findAllById(uuids);
    }

    @Override
    public User editUser(String username, EditUserRequest editUserRequest){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new RuntimeException("cannot find username");
        }
        User currentUser = user.get();
        if (editUserRequest.getPassword()!= null && !editUserRequest.getPassword().isBlank()){
            currentUser.setPassword(editUserRequest.getPassword());
        }
        if (editUserRequest.getFname()!= null && !editUserRequest.getFname().isBlank()){
            currentUser.setFname(editUserRequest.getFname());
        }
        if (editUserRequest.getLname()!= null && !editUserRequest.getLname().isBlank()){
            currentUser.setLname(editUserRequest.getLname());
        }
        if (editUserRequest.getTel()!= null && !editUserRequest.getTel().isBlank()){
            currentUser.setTel(editUserRequest.getTel());
        }
        if (editUserRequest.getEmail()!= null && !editUserRequest.getEmail().isBlank()){
            currentUser.setEmail(editUserRequest.getEmail());
        }
        return userRepository.save(currentUser);
    }
}