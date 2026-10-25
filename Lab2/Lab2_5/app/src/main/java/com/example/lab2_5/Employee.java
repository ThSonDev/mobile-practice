package com.example.lab2_5;

public class Employee {
    private String id;
    private String fullName;
    private boolean manager;

    public Employee(String id, String fullName, boolean manager) {
        this.id = id;
        this.fullName = fullName;
        this.manager = manager;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isManager() {
        return manager;
    }
}
