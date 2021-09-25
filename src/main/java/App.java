import models.Departments;
import models.News;
import models.Users;
import org.sql2o.Sql2oException;
import static spark.Spark.*;

import com.google.gson.Gson;
import dao.*;
import dao.Sql2oNewsDao;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oUsersDao;

import models.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class App {
    public static void main(String[] args) {
        Sql2oDepartmentsDao departmentsDao;
        Sql2oUsersDao usersDao;
        Sql2oNewsDao newsDao;
        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "1234");


        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);

        con = sql2o.open();
        post("/departments/new", "application/json", (req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentsDao.add(departments);
            res.status(201);
            res.type("application/json");
            return gson.toJson(departments);
        });


    }


}
