package com.shop.webstore.data.service;


import com.shop.webstore.data.exception.ResourceNotFoundException;
import com.shop.webstore.data.model.user.User;
import com.shop.webstore.data.model.user.UserDTO;
import com.shop.webstore.s3.S3Buckets;
import com.shop.webstore.s3.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final S3Buckets s3Buckets;

    public UserService(UserRepository userRepository, S3Service s3Service, S3Buckets s3Buckets) {
        this.userRepository = userRepository;
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean userExists(Long userId) {
        return userRepository.findById(userId).orElse(null) != null;
    }

    public void updateUser(Long userId, UserDTO updateRequest) throws ResourceNotFoundException {
        User user = getUserById(userId);
        user.setEmail(updateRequest.getEmail());
        user.setFirstname(updateRequest.getFirstname());
        user.setLastname(updateRequest.getLastname());
        userRepository.save(user);
    }

    public void uploadProfilePic(Long userId, MultipartFile file) {
        if (userExists(userId)) {
            try {
                String profilePicId = UUID.randomUUID().toString();
                User user = getUserById(userId);

                Map<String, String> metadata = new HashMap<>();
                metadata.put("Content-Type", file.getContentType());
                metadata.put("Content-Length", String.valueOf(file.getSize()));
                s3Service.putObject(s3Buckets.getUser(), "profile-pics/%s/%s".formatted(userId, profilePicId), file.getBytes(), metadata);

                user.setProfilePic(profilePicId);
                userRepository.save(user);
            } catch (IOException | ResourceNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public byte[] getUserProfilePic(Long userId) throws ResourceNotFoundException {
        User user = getUserById(userId);
        return s3Service.getObject(
                s3Buckets.getUser(),
                "profile-pics/%s/%s".formatted(userId, user.getProfilePic())
        );
    }

    public User getUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public User getUserByUsername(String username) throws ResourceNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
