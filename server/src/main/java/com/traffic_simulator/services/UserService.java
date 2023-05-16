package com.traffic_simulator.services;

import com.traffic_simulator.dto.UserDTO;
import com.traffic_simulator.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO UserDTO);

    User findUserByUsername(String username);

    List<UserDTO> findAllUsers();
}