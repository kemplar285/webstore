package com.shop.webstore.rest.api.response;

import com.shop.webstore.data.model.user.UserRole;

public class UserInfoResponse extends AbstractResponse {
    private Long userId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String profilePic;
    private UserRole role;

    public UserInfoResponse() {
    }

    public UserInfoResponse(Long userId, String username, String email, String firstname, String lastname, String profilePic, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePic = profilePic;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
