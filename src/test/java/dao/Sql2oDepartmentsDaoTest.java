package dao;

import models.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentsDao departmentsDao;

    @BeforeEach
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "1234");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown()  throws Exception{
        conn.close();
    }

    @Test
    void addingDepartmentsSetsId()  throws Exception {
        Users testUsers = setUpUsers();
        assertEquals(1, testUsers.getId());
    }
    
}