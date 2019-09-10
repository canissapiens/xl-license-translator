package pl.mirek.xllicensetranslator.dto;

import java.util.ArrayList;
import java.util.List;

public class SimplyDivisionDto {
    public String acronym;
    public String colorclass;
    public List<String> activeLicenses;

    public int licenseUtilization;
    public int licenseLimit;

    public SimplyDivisionDto(String acronym) {
        this.acronym = acronym;
        this.colorclass = "";
        this.activeLicenses = new ArrayList<>();
    }
}
