package com.csms.modal;

public class Role {
    private String id , name , authorize;

    public Role() {
    }

    public Role(String id, String name, String authorize) {
        this.id = id;
        this.name = name;
        this.authorize = authorize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", authorize=" + authorize + '}';
    }

}
