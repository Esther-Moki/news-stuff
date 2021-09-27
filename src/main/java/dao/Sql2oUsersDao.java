package dao;

import models.Departments;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUsersDao implements UsersDao {
    private final Sql2o sql2o;

    public Sql2oUsersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //add users
    @Override
    public void add(Users users) {
        String sql = "INSERT INTO users(username,position,role) VALUES (:username,:position,:role)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(users)
                    .executeUpdate()
                    .getKey();
            users.setId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //many to many relationship between users and departments
    @Override
    public void addUsersToDepartments(Users users, Departments departments) {
        String sql = "INSERT INTO departments_users (departmentsid, usersid) VALUES (:departmentsId, :usersId)";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("departmentsId", departments.getId())
                    .addParameter("usersId", users.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //get all users
    @Override
    public List<Users> getAll() {
        Connection conn = sql2o.open();
        return conn.createQuery("SELECT * FROM users")
                .executeAndFetch(Users.class);
    }


//    @Override
//    public List<Users> getAllUsersForADepartments(int departmentsId) {
//        try (Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM users WHERE departmentsId = :departmentsId")
//                    .addParameter("departmentsId", departmentsId)
//                    .executeAndFetch(Users.class);
//        }
//    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }

    @Override
    public Users findById(int id) {
        try (Connection conn = sql2o.open()) {
            String sql = ("SELECT * FROM users WHERE id=:id");
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Users.class);
        }
    }
}









