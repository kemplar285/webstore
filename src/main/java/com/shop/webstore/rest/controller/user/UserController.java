package com.shop.webstore.rest.controller.user;

import com.shop.webstore.data.exception.ResourceNotFoundException;
import com.shop.webstore.data.model.user.UserDTO;
import com.shop.webstore.data.service.UserService;
import com.shop.webstore.rest.api.response.GeneralApiResponse;
import com.shop.webstore.rest.api.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("{username}")
//    public ResponseEntity<?> getUserInfo(@PathVariable("username") String username){
//        UserDTO response = new UserDTO();
//            User user = userService.getUserByUsername(username);
//            response.setEmail(user.getEmail());
//            response.setFirstname(user.getFirstname());
//            response.setLastname(user.getLastname());
//            response.setUsername(user.getUsername());
//            response.setUserId(user.getId());
//            response.setResponseCode(ResponseCode.OK);
//            response.setMessage("User info found!");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        GeneralApiResponse response = new GeneralApiResponse();
        try {
            userService.deleteUser(userId);
            response.setResponseCode(ResponseCode.OK);
            response.setMessage("Article updated");
        }
        catch (Exception e){
            response.setResponseCode(ResponseCode.SYSTEM_ERROR);
            response.setMessage("Update failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PutMapping("{userId}")
//    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest updateRequest) {
//        GeneralApiResponse response = new GeneralApiResponse();
//        try {
//            userService.updateUser(userId, updateRequest);
//            response.setResponseCode(ResponseCode.OK);
//            response.setMessage("User updated!");
//        }
//        catch (Exception e){
//            response.setResponseCode(ResponseCode.SYSTEM_ERROR);
//            response.setMessage("Update failed");
//        }
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PostMapping(value = "{userId}/profile-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GeneralApiResponse> uploadUserProfilePic(@PathVariable("userId") Long userId,
                                                                   @RequestParam("file") MultipartFile file) {
        GeneralApiResponse response = new GeneralApiResponse();
        if(!file.isEmpty()) {
            userService.uploadProfilePic(userId, file);
            response.setMessage("File Loaded!");
            response.setResponseCode(ResponseCode.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setResponseCode(ResponseCode.INVALID_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "{userId}/profile-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserProfilePic(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
        return userService.getUserProfilePic(userId);
    }

}
