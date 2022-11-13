package com.thebongcoder.Blog.Application.services;

import com.thebongcoder.Blog.Application.dto.UserRequestDTO;
import com.thebongcoder.Blog.Application.dto.UserResponseDTO;
import com.thebongcoder.Blog.Application.entities.User;
import com.thebongcoder.Blog.Application.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(UserRequestDTO userRequestDTO) {
        Boolean existsByEmail = userRepository.existsByEmail(userRequestDTO.getEmail().toLowerCase());
        if (existsByEmail) {
            return "Email already exists";
        }

        int compareValue = StringUtils.compare(userRequestDTO.getPassword(), userRequestDTO.getConfirmPassword());
        if (compareValue != 0) {
            return "please check password";
        }
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setAbout(userRequestDTO.getAbout());
        return "User created successfully";
    }

    public String updateUser(UserRequestDTO userRequestDTO, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return "user not found";
        }
        User user = optionalUser.get();
        if (StringUtils.isNotBlank(userRequestDTO.getName())) {
            user.setName(userRequestDTO.getName());
        }
        if (StringUtils.isNotBlank(userRequestDTO.getEmail())) {
            user.setEmail(userRequestDTO.getEmail());
        }
        if (StringUtils.isNotBlank(userRequestDTO.getAbout())) {
            user.setAbout(userRequestDTO.getAbout());
        }
        userRepository.save(user);
        return "User updated successfully";
    }

    public Object getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return "user not found";
        }
        User user = optionalUser.get();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setAbout(user.getAbout());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            return null;
        }
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user : allUsers) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAbout(user.getAbout());
            userResponseDTOList.add(userResponseDTO);
        }
        return userResponseDTOList;
    }

    public String deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return "user not found";
        }
        User user = optionalUser.get();
        userRepository.delete(user);
        return "user deleted successfully";
    }
}
