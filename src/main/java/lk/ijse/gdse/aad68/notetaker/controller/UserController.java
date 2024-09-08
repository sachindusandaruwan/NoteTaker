package lk.ijse.gdse.aad68.notetaker.controller;

import lk.ijse.gdse.aad68.notetaker.dto.NoteDTO;
import lk.ijse.gdse.aad68.notetaker.dto.UserDTO;
import lk.ijse.gdse.aad68.notetaker.service.UserService;
import lk.ijse.gdse.aad68.notetaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    //save user
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic) {

        //handle profile picture
        String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);

        //build the user object
        var buildUserDTO = new UserDTO();
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProfilePic);

        //send to the service layer
       // return new ResponseEntity<>(userService.saveUser(buildUserDTO), HttpStatus.CREATED);
        String savedStatus = userService.saveUser(buildUserDTO);
        if (savedStatus.contains("user saved successfully")) {
            return new ResponseEntity<>(savedStatus, HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>(savedStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        return userService.deleteUser(userId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable("id") String userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(
            @PathVariable("id") String id,
            @RequestPart("updateFirstName") String updateFirstName,
            @RequestPart("updateLastName") String updateLastName,
            @RequestPart("updateEmail") String updateEmail,
            @RequestPart("updatePassword") String updatePassword,
            @RequestPart("updateProfilePic") String updateProfilePic
    ) {
        String base64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
        var updateUser=new UserDTO();
        updateUser.setUserId(id);
        updateUser.setFirstName(updateFirstName);
        updateUser.setLastName(updateLastName);
        updateUser.setEmail(updateEmail);
        updateUser.setPassword(updatePassword);
        updateUser.setProfilePic(base64ProfilePic);

        return userService.updateUser(updateUser)? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
