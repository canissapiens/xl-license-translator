package pl.mirek.xllicensetranslator.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Organization {
    private String name;
    private Set<Division> divisions = new HashSet<>();

    public Organization() {
    }

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(Set<Division> divisions) {
        this.divisions = divisions;
    }

    // http://193.239.184.168:5150/

}
