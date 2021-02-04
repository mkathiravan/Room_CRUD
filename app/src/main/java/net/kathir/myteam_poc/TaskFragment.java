package net.kathir.myteam_poc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TaskFragment extends Fragment {

    private static final String TAG = TaskFragment.class.getSimpleName();

    public TaskFragment() {
        // Required empty public constructor
    }

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.employee_main, container, false);
        return root;
    }
}
