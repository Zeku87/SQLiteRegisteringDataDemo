package com.demo.joseezequielgallardo.sqliteregisteringdatademo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="company";
    private static final int DATABASE_VERSION = 1;

    private static final String EMPLOYEE_TABLE_NAME = "employee";
    private static final String EMPLOYEE_TABLE_SQL = "create table " + EMPLOYEE_TABLE_NAME + "(name TEXT,email TEXT primary key,password TEXT,role TEXT,department TEXT)";


    private static DatabaseHelper databaseHelper = null;

    public static DatabaseHelper getInstance(Context context){
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper(context);
        }

        return databaseHelper;
    }

    private final Context context;

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EMPLOYEE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    ///// INSERTION /////

    public void insertEmployee(String name, String email, String password, String role, String department){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("role", role);
        contentValues.put("password", password);
        contentValues.put("department", department);

        db.insert(EMPLOYEE_TABLE_NAME, null, contentValues);

        db.close();
    }

    ///// SELECTION /////

    public List<Employee> getAllEmployees(){

        List<Employee> employees = new ArrayList<>();

        String query = "SELECT * FROM " + EMPLOYEE_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Employee employee = new Employee();
                employee.setName(cursor.getString(0));
                employee.setEmail(cursor.getString(1));
                employee.setRole(cursor.getString(2));
                employee.setPassword(cursor.getString(3));
                employee.setDepartment(cursor.getString(4));

                employees.add(employee);

            }while (cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return employees;
    }

    public int getEmployeesCount(){

        int employeesCount = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT count(*) FROM " + EMPLOYEE_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null){
            cursor.moveToFirst();
            employeesCount = cursor.getInt(0);
            cursor.close();
        }

        db.close();

        return employeesCount;
    }

    ///// DELETION /////

    public void dropAnEmployee(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EMPLOYEE_TABLE_NAME, "email = ?", new String[] {email});
        db.close();
    }

}
