package lk.ijse.gdse.aad68.notetaker.service;

import lk.ijse.gdse.aad68.notetaker.custom.UserResponse;
import lk.ijse.gdse.aad68.notetaker.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
