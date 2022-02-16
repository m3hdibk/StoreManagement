package com.mehdi.storemanagement.service.impl;

import com.mehdi.storemanagement.model.User;
import com.mehdi.storemanagement.model.dto.UserData;
import com.mehdi.storemanagement.repository.UserRepository;
import com.mehdi.storemanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public record UserServiceImpl(UserRepository userRepository) implements UserService {


    @Override
    public void addUser(UserData userData) {
        userRepository.save(userData.convertToEntity());
    }

    @Override
    public List<UserData> getUsersByType(int type) {
        List<User> users = userRepository.findByType(type);
        return users.stream().map(User::convertToData).collect(Collectors.toList());
    }
}
