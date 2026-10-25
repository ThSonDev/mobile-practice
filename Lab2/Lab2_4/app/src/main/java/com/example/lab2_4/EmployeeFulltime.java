package com.example.lab2_4;

public class EmployeeFulltime extends Employee {
    @Override
    public double tinhLuong() {
        return 500;
    }

    @Override
    public String toString() {
        return super.toString() + " -->FullTime=" + tinhLuong();
    }
}
