package net.kathir.myteam_poc;

import android.content.Intent;
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

public class JobSiteFragment extends Fragment {

    FloatingActionButton addButton;
    RecyclerView recyclerView;

    private static final String TAG = JobSiteFragment.class.getSimpleName();

    public JobSiteFragment() {
        // Required empty public constructor
    }

    public static JobSiteFragment newInstance() {
        return new JobSiteFragment();
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

                Intent intent = new Intent(getActivity(),CreateJobSiteActivity.class);
                intent.putExtra("page_from","create_action");
                startActivity(intent);
            }
        });



        return root;


    }
}
