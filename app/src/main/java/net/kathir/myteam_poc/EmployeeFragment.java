package net.kathir.myteam_poc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EmployeeFragment extends Fragment {

    FloatingActionButton addButton;
    RecyclerView recyclerView;

    private static final String TAG = EmployeeFragment.class.getSimpleName();

    public EmployeeFragment() {
        // Required empty public constructor
    }

    public static EmployeeFragment newInstance() {
        return new EmployeeFragment();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.employee_main, container, false);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addButton = (FloatingActionButton)root.findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),CreateEmployee.class);
                intent.putExtra("page_from","create_action");
                startActivity(intent);
            }
        });

        getTasks();

        return root;
    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<EmployeeTable>> {

            @Override
            protected List<EmployeeTable> doInBackground(Void... voids) {
                List<EmployeeTable> taskList = DatabaseClient
                        .getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .employeeDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<EmployeeTable> tasks) {
                super.onPostExecute(tasks);
                EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
