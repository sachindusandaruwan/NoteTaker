package lk.ijse.gdse.aad68.notetaker.service;

import lk.ijse.gdse.aad68.notetaker.dao.UserDao;
import lk.ijse.gdse.aad68.notetaker.dto.UserDTO;
import lk.ijse.gdse.aad68.notetaker.entity.UserEntity;
import lk.ijse.gdse.aad68.notetaker.util.AppUtil;
import lk.ijse.gdse.aad68.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        userDao.save(mapping.convertToEntity(userDTO));

        return "User Saved Successfully";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        if(userDao.existsById(userId)) {
            userDao.deleteById(userId);
            System.out.println("User Deleted Successfully");
            return true;
        }else {
            return false;
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        UserEntity userEntityByUserId = userDao.getUserEntityByUserId(userId);
        return mapping.convertToUserDTO(userEntityByUserId);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return mapping.convertToUserDTOS(userDao.findAll());
    }
}
