package com.traffic_simulator.services;

import com.traffic_simulator.dto.UserDTO;
import com.traffic_simulator.models.Role;
import com.traffic_simulator.models.User;
import com.traffic_simulator.repository.RoleRepository;
import com.traffic_simulator.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO UserDTO) {
        User user = new User();
        user.setUsername(UserDTO.getUsername());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(UserDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO UserDTO = new UserDTO();
        UserDTO.setId(user.getId());
        UserDTO.setUsername(user.getUsername());
        return UserDTO;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
