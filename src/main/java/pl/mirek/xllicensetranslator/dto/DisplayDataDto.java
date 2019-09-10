package pl.mirek.xllicensetranslator.dto;

import java.util.ArrayList;
import java.util.List;

public class DisplayDataDto {
    public String allUsedLicenses;
    public List<SimplyDivisionDto> divisions;

    public DisplayDataDto(String allUsedLicenses) {
        this.allUsedLicenses = allUsedLicenses;
        divisions = new ArrayList<>();
    }
}
