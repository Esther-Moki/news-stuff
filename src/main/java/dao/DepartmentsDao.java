package dao;

import models.Departments;
import models.News;

import java.util.List;

public interface DepartmentsDao {
    //create
    void add (Departments departments);

    //read
    List<Departments> getAll();
    Departments findById(int id);
    List<News> getAllNewsForADepartments(int departmentId);
    List<News> getAllUsersForADepartments(int departmentId);

    //update
    void update(int id, String name, String description, String employees);

    //delete
    void deleteById(int id);
    void clearAll();


}


