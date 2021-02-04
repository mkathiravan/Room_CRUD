package net.kathir.myteam_poc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CreateEmployee extends AppCompatActivity {

    private static final String TAG = CreateEmployee.class.getSimpleName();

    EditText emp_phoneNo, emp_name, emp_desc, emp_type;

    Button saveBtn;

    EmployeeTable employeeTable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_create);

        emp_phoneNo = (EditText)findViewById(R.id.employee_phhno);
        emp_name = (EditText)findViewById(R.id.employee_name);
        emp_desc = (EditText)findViewById(R.id.employee_desc);
        emp_type = (EditText)findViewById(R.id.employee_type);
        saveBtn = (Button)findViewById(R.id.button_save);

        try {

            String page_from = getIntent().getStringExtra("page_from");

            employeeTable = (EmployeeTable) getIntent().getSerializableExtra("employee");

            if (page_from.equals("update_action"))
            {
                emp_phoneNo.setText(employeeTable.getEmp_phno());
                emp_name.setText(employeeTable.getEmp_name());
                emp_desc.setText(employeeTable.getEmp_desc());
                emp_type.setText(employeeTable.getEmp_type());

                saveBtn.setText("Update");

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d(TAG,"EMPloyee_Updated ");

                        updateTask(employeeTable);
                    }
                });



            }
            else if(page_from.equals("create_action"))
            {
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        saveTask();
                    }
                });

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    private void updateTask(final EmployeeTable employeeTable) {
        final String sEmpPhNo = emp_phoneNo.getText().toString().trim();
        final String sEmpName = emp_name.getText().toString().trim();
        final String sEmpDesc = emp_desc.getText().toString().trim();
        final String sEmpType = emp_type.getText().toString().trim();

        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                employeeTable.setEmp_phno(sEmpPhNo);
                employeeTable.setEmp_name(sEmpName);
                employeeTable.setEmp_desc(sEmpDesc);
                employeeTable.setEmp_type(sEmpType);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .employeeDao()
                        //.updateEmployee(employeeTable.getEmp_phno(),employeeTable.getEmp_name(),employeeTable.getEmp_desc(),employeeTable.getEmp_type(), employeeTable.get_id());
                        .update(employeeTable);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(CreateEmployee.this, MainActivity.class));
            }
        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }



    private void saveTask() {

        final String sEmp_phoneNo = emp_phoneNo.getText().toString().trim();
        final String sEmp_name = emp_name.getText().toString().trim();
        final String sEmp_desc = emp_desc.getText().toString().trim();
        final String sEmp_type = emp_type.getText().toString().trim();

        if (sEmp_phoneNo.isEmpty()) {
            emp_phoneNo.setError("Phone No required");
            emp_phoneNo.requestFocus();
            return;
        }

        if (sEmp_name.isEmpty()) {
            emp_name.setError("Name required");
            emp_name.requestFocus();
            return;
        }

        if (sEmp_desc.isEmpty()) {
            emp_desc.setError("Description required");
            emp_desc.requestFocus();
            return;
        }

        if (sEmp_type.isEmpty()) {
            emp_type.setError("Type Required");
            emp_type.requestFocus();
            return;
        }


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task

                EmployeeTable employeeTable = new EmployeeTable();
                employeeTable.setEmp_phno(sEmp_phoneNo);
                employeeTable.setEmp_name(sEmp_name);
                employeeTable.setEmp_desc(sEmp_desc);
                employeeTable.setEmp_type(sEmp_type);



                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .employeeDao()
                        .insert(employeeTable);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateEmployee.this);

                builder.setMessage(R.string.dialog_message);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteActions();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();



                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteActions() {


        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .employeeDao()
                        .deleteEmployeeId(employeeTable.get_id());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }
}
