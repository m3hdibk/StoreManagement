package com.mehdi.storemanagement.service;


import com.mehdi.storemanagement.model.dto.UserData;

import java.util.List;

public interface UserService {

    void addUser(UserData userData);
    List<UserData> getUsersByType(int type);
}
