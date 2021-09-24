package models;

import java.util.Objects;

public class Departments {
    private String name;
    private String description;
    private String employees;
    private int id;

    public Departments(String name, String description, String employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmployees() {
        return employees;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments departments = (Departments) o;
        return id == departments.id &&
                Objects.equals(name, departments.name) &&
                Objects.equals(description, departments.description) &&
                Objects.equals(employees, departments.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, employees, id);
    }
}
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Departments)) return false;
//        Departments departments = (Departments) o;
//        return id == departments.id && Objects.equals(name, departments.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, id);
//    }

