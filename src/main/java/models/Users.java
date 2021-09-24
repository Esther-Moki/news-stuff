package models;

import java.util.Objects;

public class Users {
    private String username;
    private String position;
    private String role;
    private int id;
  //  private int departmentsId; //will be used to connect Departments to Users (one-to-many)

    public Users(String username,String position,String role,int departmentsId){
        this.username = username;
        this.position = position;
        this.role = role;
       // this.departmentsId = departmentsId;
    }

    public String getUsername() {
        return username;
    }

    public String getPosition() {
        return position;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

//    public int getDepartmentsId() {
//        return departmentsId;
//    }
//
//    public void setDepartmentsId(int departmentsId) {
//        this.departmentsId = departmentsId;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users )) return false;
        Users users = (Users) o;
        return  id == users.id &&
               // departmentsId == users.departmentsId &&
                Objects.equals(username, users.username) &&
                Objects.equals(position, users.position) &&
                Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, position, role, id);
    }
}
