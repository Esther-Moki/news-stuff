package dao;

import models.News;
import models.Users;

import java.util.List;

public interface NewsDao {
    //create
    void add(News news);

    //read
    List<News> getAll();
    News findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
