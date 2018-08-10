package com.demo.joseezequielgallardo.sqliteregisteringdatademo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricTestRunner.class)
public class DatabaseTest{

    DatabaseHelper db;

    @Before
    public void setUp(){
        db = DatabaseHelper.getInstance(RuntimeEnvironment.application);
    }

    @Test
    public void databaseCasesTest(){

        //Let's insert an employee and count records

        db.insertEmployee("Juan", "juan@mail.com",
                "123", "Developer", "QA");

        db.insertEmployee("maria", "mari@mail.com",
                "123", "Developer", "QA");

        System.out.println("Employees count: " + db.getEmployeesCount());

        db.close();
    }
}