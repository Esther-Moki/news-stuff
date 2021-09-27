package dao;

import models.Departments;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
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
        try (Connection conn=sql2o.open()){
            String sql=("SELECT * FROM staff WHERE id=:id");
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Departments.class);
        }
    }


    //many to many between news and departments
    @Override
    public List<News> getAllNewsForADepartments(int departmentsId) {

        ArrayList<News> news = new ArrayList<>();

        String joinQuery = "SELECT newsid FROM departments_news WHERE departmentsid = :departmentsId";

        try (Connection conn = sql2o.open()) {
            List<Integer> allNewsIds = conn.createQuery(joinQuery)
                    .addParameter("departmentsid", departmentsId)
                    .executeAndFetch(Integer.class);
            for (Integer newsId : allNewsIds){
                String usersQuery = "SELECT * FROM news WHERE id = :newsId";
                news.add(
                        conn.createQuery(usersQuery)
                                .addParameter("newsId", newsId)
                                .executeAndFetchFirst(News.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

        return news;
    }

    //many to many between users and departments
    @Override
    public List<Users> getAllUsersForADepartments(int departmentsId) {
        ArrayList<Users> users = new ArrayList<>();

        String joinQuery = "SELECT usersid FROM departments_users WHERE departmentsid = :departmentsId";

        try (Connection conn = sql2o.open()) {
            List<Integer> allUsersIds = conn.createQuery(joinQuery)
                    .addParameter("departmentsid", departmentsId)
                    .executeAndFetch(Integer.class);
            for (Integer usersId : allUsersIds){
                String usersQuery = "SELECT * FROM users WHERE id = :usersId";
                users.add(
                        conn.createQuery(usersQuery)
                                .addParameter("usersId", usersId)
                                .executeAndFetchFirst(Users.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

        return users;
    }

    //updating a department
    @Override
    public void update(int id, String name, String description, String employees) {

    }

    //deleting a department by Id from both the single and join tables;
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM departments WHERE id=:id";
        String deleteJoin = "DELETE from departmets_users WHERE departmentsid = :departmentsId";
        try(Connection conn=sql2o.open()) {
            conn.createQuery(sql,true)
                .addParameter("id",id)
                .executeUpdate();
            conn.createQuery(deleteJoin)
                    .addParameter("departmentsId", id)
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



//