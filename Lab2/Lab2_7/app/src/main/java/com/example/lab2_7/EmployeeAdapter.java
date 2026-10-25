package com.example.lab2_7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {
    private List<Employee> employees;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    public static class EmployeeHolder extends RecyclerView.ViewHolder {
        TextView tvFullName;
        TextView tvPosition;
        ImageView ivManager;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.item_employee_tv_fullname);
            tvPosition = (TextView) itemView.findViewById(R.id.item_employee_tv_position);
            ivManager = (ImageView) itemView.findViewById(R.id.item_employee_iv_manager);
        }
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee = employees.get(position);

        holder.tvFullName.setText(employee.getFullName());

        if (employee.isManager()) {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
        } else {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText(R.string.staff);
        }

        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.white);
        } else {
            holder.itemView.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}
