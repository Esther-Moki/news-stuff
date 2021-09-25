package dao;

import models.Departments;
import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentsDaoTest {
    private Connection conn;
    public Sql2oNewsDao newsDao;
     public Sql2oUsersDao usersDao;
    public Sql2oDepartmentsDao departmentsDao;

    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "1234");
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown()  throws Exception{
        conn.close();
    }

    @Test
    public void addingDepartmentsSetId()  throws Exception {
        Departments testDepartments = setUpDepartments();
        assertEquals(1,  testDepartments.getId());
    }

//    @Test
//      public void getAll() throws Exception {
//         Departments departments1 =  setUpDepartments();
//         Departments departments2 =  setUpDepartments();
//        assertEquals(2,     Departments.getAll().size());
//      }
//
    private Departments setUpDepartments() {
        return null;
    }


}