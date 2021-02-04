package net.kathir.myteam_poc;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface EmployeeDao {

    //Insert HeartBeatTable
    @Insert(onConflict = REPLACE)
    void insert(EmployeeTable employeeTable);

    //Update HeartBeatTable

    @Update
    void update(EmployeeTable employeeTables);



    @Query("UPDATE employeeTable SET emp_phno = :emp_phno, emp_name = :emp_name, emp_desc = :emp_desc, emp_type = :emp_type WHERE _id = :id")
    void updateEmployee(String emp_phno, String emp_name, String emp_desc, String emp_type, int id);



    //Delete data by WorkOrderTable id
    @Query("DELETE FROM employeeTable WHERE _id==:id")
    void deleteEmployeeId(int id);


    //Delete HeartBeatTable
    @Delete
    void delete(EmployeeTable encryptedHeartBeatTable);

    //Delete all data from HeartBeatTable;
    @Query("DELETE FROM employeeTable")
    void deleteAllWorkOrderEntries();

    @Query("SELECT * FROM employeeTable")
    List<EmployeeTable> getAll();


    //Delete data by WorkOrderTable id
    @Query("DELETE FROM employeeTable WHERE _id==:id")
    void deleteAllHeartBeatEntriesUnderId(int id);

}
