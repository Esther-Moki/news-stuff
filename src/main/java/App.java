import models.Departments;

import static spark.Spark.*;

import com.google.gson.Gson;
import dao.*;
import dao.Sql2oNewsDao;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oUsersDao;


import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class App {
    public static void main(String[] args) {
        Sql2oDepartmentsDao departmentsDao;
        Sql2oUsersDao usersDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();


       String connectionString = "jdbc:h2:~/news_information.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        //departments
        //CREATE
        post("/departments/new", "application/json", (req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentsDao.add(departments);
            res.status(201);
            res.type("application/json");
            return gson.toJson(departments);
        });

        //READ
        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(departmentsDao.getAll());//send it back to be displayed
        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int departmentsid = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentsDao.findById(departmentsid));
        });

         //users
        //CREATE
        post("/users/new", "application/json", (req, res) -> {
            Users users = gson.fromJson(req.body(), Users.class);
            usersDao.add(users);
            res.status(201);
            res.type("application/json");
            return gson.toJson(users);
        });

        //READ
        get("/users", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(usersDao.getAll());//send it back to be displayed
        });

        get("/users/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int usersid = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(usersDao.findById(usersid));
        });

        //news
        //CREATE
        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });

        //READ
        get("/news", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(newsDao.getAll());//send it back to be displayed
        });

        get("/news/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int newsid = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(newsDao.findById(newsid));
        });




        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });
    }

}




//  usersDao = new Sql2oUsersDao(sql2o);
//  newsDao = new Sql2oNewsDao(sql2o);



