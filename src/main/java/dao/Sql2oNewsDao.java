package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;
    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


  //add some news relating to a department.
    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (topic, content,departmentsId, usersid) VALUES (:topic, :content, :departmentsId, :usersId)";
        try(Connection conn=sql2o.open()){
            int id=(int) conn.createQuery(sql,true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    // Method to collect all the news
    @Override
    public List<News> getAll() {
        Connection conn=sql2o.open();
        return conn.createQuery("SELECT * FROM news")
                .executeAndFetch(News.class);
    }


    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
