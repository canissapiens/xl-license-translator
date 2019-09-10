package pl.mirek.xllicensetranslator.model;

import java.util.*;

public class Division {
    private String name;
    private int limit = 0;
    private Set<Employee> employees = new HashSet<>();

    public Division() {
    }

    public Division(String name) {
        this.name = name;
    }

    public Division(String name, int limit, Set<Employee> employees) {
        this.name = name;
        this.limit = limit;
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
