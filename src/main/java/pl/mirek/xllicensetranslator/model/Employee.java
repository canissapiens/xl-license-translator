package pl.mirek.xllicensetranslator.model;

public class Employee  {

    private String acronym;
    private String firstName;
    private String lastName;

    public Employee(String acronym) {
        this.acronym = acronym;
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }


}
