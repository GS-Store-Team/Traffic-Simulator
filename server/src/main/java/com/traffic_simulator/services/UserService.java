package com.traffic_simulator.services;

import com.traffic_simulator.dto.UserDTO;
import com.traffic_simulator.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDto);
    List<UserDTO> findAllUsers();

    User findUserByUsername(String username);
}