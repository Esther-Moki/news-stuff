package dao;

import models.Departments;
import models.Users;

import java.util.List;

public interface UsersDao {
    //create
    void add(Users users);
    void addUsersToDepartments(Users users, Departments departments);

    //read
    List<Users> getAll();

    //delete
    void deleteById(int id);
    void clearAll();

}


