package dao;

import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao{
    private final Sql2o sql2o;

    public Sql2oDepartmentsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


   //create a new department
    @Override
    public void add(Departments departments) {
        String sql = "ADD INTO  departments (name,description, employees) VALUES (:name,:description,:employees,:phone)";
        try (Connection conn=sql2o.open();){
            int id=(int)conn.createQuery(sql,true)
                    .bind( departments)
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    //many-to-many
    @Override
    public List<Departments> getAll() {
        Connection conn=sql2o.open();
        return conn.createQuery("SELECT * FROM departments")
                .executeAndFetch(Departments.class);
    }


    //finding any Departments by ID
    @Override
    public Departments findById(int id) {
        try (Connection con=sql2o.open()){
            String sql=("SELECT * FROM staff WHERE id=:id");
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Departments.class);
        }
    }

    //many to many
    @Override
    public List<News> getAllNewsForADepartments(int departmentId) {
        return null;
    }

    //many to many
    @Override
    public List<News> getAllUsersForADepartments(int departmentId) {
        return null;
    }

    //updating a department
    @Override
    public void update(int id, String name, String description, String employees) {

    }

    //deleting a department by Id;
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM departments WHERE id=:id";
        try(Connection conn=sql2o.open();)
        {conn.createQuery(sql,true)
                .addParameter("id",id)
                .executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void clearAll() {
        String sql="DELETE * FROM departments";
        try(Connection conn=sql2o.open();){
            conn.createQuery(sql,true)
                    .executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
