package com.telemedine.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String fullname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String phone;

    @NotBlank
    @Size(min = 3, max = 20)
    private String photo;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getFullname() { return fullname; }

    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
