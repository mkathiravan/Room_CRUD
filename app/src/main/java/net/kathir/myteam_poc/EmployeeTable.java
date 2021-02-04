package net.kathir.myteam_poc;


import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "employeeTable")
public class EmployeeTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "emp_phno")
    private String emp_phno;

    @ColumnInfo(name = "emp_name")
    private String emp_name;

    @ColumnInfo(name = "emp_desc")
    private String emp_desc;

    @ColumnInfo(name = "emp_type")
    private String emp_type;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEmp_phno() {
        return emp_phno;
    }

    public void setEmp_phno(String emp_phno) {
        this.emp_phno = emp_phno;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_desc() {
        return emp_desc;
    }

    public void setEmp_desc(String emp_desc) {
        this.emp_desc = emp_desc;
    }

    public String getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }


}
