package lk.ijse.gdse.aad68.notetaker.service;

import lk.ijse.gdse.aad68.notetaker.custom.UserErrorResponse;
import lk.ijse.gdse.aad68.notetaker.custom.UserResponse;
import lk.ijse.gdse.aad68.notetaker.dao.UserDao;
import lk.ijse.gdse.aad68.notetaker.dto.impl.UserDTO;
import lk.ijse.gdse.aad68.notetaker.entity.UserEntity;
import lk.ijse.gdse.aad68.notetaker.exception.UserNotFoundException;
import lk.ijse.gdse.aad68.notetaker.util.AppUtil;
import lk.ijse.gdse.aad68.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        /*userDTO.setUserId(AppUtil.createUserId());
        userDao.save(mapping.convertToEntity(userDTO));

        return "User Saved Successfully";*/
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity saveUser=userDao.save(mapping.convertToEntity(userDTO));
        if (saveUser!=null && saveUser.getUserId()!=null) {
            return "User saved successfully";
        }
        else {
            return "User not saved";
        }
    }

    @Override
    public void updateUser( UserDTO userDTO) {
        //optional use kare null point exception handle karanna
        Optional<UserEntity> tempUser=userDao.findById(userDTO.getUserId());
        if(!tempUser.isPresent()){
//            return false;
            throw new UserNotFoundException("User not found");
        }else {
            tempUser.get().setFirstName(userDTO.getFirstName());
            tempUser.get().setLastName(userDTO.getLastName());
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
            tempUser.get().setProfilePic(userDTO.getProfilePic());
            //tempUser.get().setNotes(userDTO.getNotes());
        }

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
    public UserResponse getSelectedUser(String userId) {
        if (userDao.existsById(userId)) {
            UserEntity userEntityByUserId = userDao.getUserEntityByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
        } else {
            return new UserErrorResponse(0, "User not found");
        }
    }
    @Override
    public List<UserDTO> getAllUsers() {

        return mapping.convertToUserDTOS(userDao.findAll());
    }
}
