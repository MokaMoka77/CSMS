package com.khoabug.coffeshop.user.model;

import com.khoabug.coffeshop.common.model.AbstractModel;
import com.khoabug.coffeshop.role.model.Role;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : DangKhoa
 * @since : 3/6/2023, Mon
 **/
public class User extends AbstractModel<User> implements Serializable {
    private String email;
    private String password;
    private boolean gender;
    private String name;
    private Date dob;
    private String phone;
    private Role role;

    public User() {
    }

    public User(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}



