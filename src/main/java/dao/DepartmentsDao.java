package dao;

import models.Departments;
import models.News;
import models.Users;

import java.util.ArrayList;
import java.util.List;

public interface DepartmentsDao {
    //create
    void add (Departments departments);

    //read
    List<Departments> getAll();
    Departments findById(int id);

    List<News> getAllNewsForADepartments(int departmentsid);
    List<Users> getAllUsersForADepartments(int departmentsid);

    //update
    void update(int id, String name, String description, String employees);

    //delete
    void deleteById(int id);
    void clearAll();
}


