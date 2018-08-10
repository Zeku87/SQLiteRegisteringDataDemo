package com.demo.joseezequielgallardo.sqliteregisteringdatademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        databaseHelper.insertEmployee("Zeku", "zeku@mail.com", "zeku1987", "Developer",
                "QA");
        List<Employee> employees = databaseHelper.getAllEmployees();
        for (Employee employee : employees){
            Log.d(TAG,employee.getName());
            Log.d(TAG,employee.getEmail());
            Log.d(TAG,employee.getRole());
            Log.d(TAG,employee.getPassword());
            Log.d(TAG,employee.getDepartment());
        }
    }
}
