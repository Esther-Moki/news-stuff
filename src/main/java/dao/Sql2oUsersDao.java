package dao;

import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oUsersDao implements UsersDao{
    private final Sql2o sql2o;

    public Sql2oUsersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //add users
    @Override
    public void add(Users users) {
        String sql="INSERT INTO users(username,position,role) VALUES (:username,:position,:role)";
        try (Connection conn =sql2o.open()){
            int id=(int)conn.createQuery(sql,true)
                    .bind(users)
                    .executeUpdate()
                    .getKey();
            users.setId(id);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //get all users
    @Override
    public List<Users> getAll() {
        Connection conn=sql2o.open();
        return conn.createQuery("SELECT * FROM users")
                .executeAndFetch(Users.class);
    }

    @Override
    public List<Users> getAllUsersForADepartments(int departmentId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
