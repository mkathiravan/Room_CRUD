package net.kathir.myteam_poc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context mCtx;
    private List<EmployeeTable> taskList;

    public EmployeeAdapter(Context mCtx, List<EmployeeTable> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.employee_list, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {


        EmployeeTable employeeTable = taskList.get(position);
        holder.textViewEmpPhNo.setText(employeeTable.getEmp_phno());
        holder.textViewEmpName.setText(employeeTable.getEmp_name());
        holder.textViewEmpDesc.setText(employeeTable.getEmp_desc());
        holder.textViewEmpType.setText(employeeTable.getEmp_type());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }



    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewEmpPhNo, textViewEmpName, textViewEmpDesc, textViewEmpType;

        public EmployeeViewHolder(View itemView) {
            super(itemView);

            textViewEmpPhNo = itemView.findViewById(R.id.text_empPhoneNo);
            textViewEmpName = itemView.findViewById(R.id.text_empName);
            textViewEmpDesc = itemView.findViewById(R.id.text_empDesc);
            textViewEmpType = itemView.findViewById(R.id.text_empType);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            EmployeeTable employee = taskList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, CreateEmployee.class);
            intent.putExtra("page_from","update_action");
            intent.putExtra("employee", employee);
            mCtx.startActivity(intent);

        }
    }
}
