package pl.mirek.xllicensetranslator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilityService {

    private OrganizationService service;

    @Autowired
    public UtilityService(OrganizationService service) {
        this.service = service;
    }

    public void initOrganization() {

        service.addEmployee("GK", "IPC");
        service.addEmployee("KS", "IPC");
        service.addEmployee("MO", "IPC");
        service.addEmployee("AJ", "IPC");
        service.addEmployee("JuU", "IPC");
        service.addEmployee("WiP", "IPC");
        service.addEmployee("LO", "IPC");
        service.addEmployee("KM", "IPC");
        service.addEmployee("KCh", "IPC");
        service.addEmployee("MW", "IPC");
        service.addEmployee("PZ", "IPC");
        service.addEmployee("LuZ", "IPC");
        service.addEmployee("TW", "SHROFF");
        service.addEmployee("WK", "SHROFF");
        service.addEmployee("JD", "SHROFF");
        service.addEmployee("DG", "SHROFF");
        service.addEmployee("PiS", "SHROFF");
        service.addEmployee("KC", "FLASH");
        service.addEmployee("LZ", "FLASH");
        service.addEmployee("DK", "FLASH");
        service.addEmployee("PiS", "FLASH");

        service.addEmployee("PK", "FOTO");
        service.addEmployee("AK", "FOTO");
        service.addEmployee("AN", "FOTO");
        service.addEmployee("MR", "FOTO");
        service.addEmployee("WD", "FOTO");
        service.addEmployee("LW", "FOTO");
        service.addEmployee("EJ", "FOTO");
        service.addEmployee("MG", "FOTO");
        service.addEmployee("ST", "FOTO");
        service.addEmployee("XX", "FOTO");
        service.addEmployee("IO", "FOTO");
        service.addEmployee("EK", "FOTO");


        service.addEmployee("MH", "DIMIL");
        service.addEmployee("KG", "DIMIL");
        service.addEmployee("IC", "mar");
        service.addEmployee("MOC", "mar");
        service.addEmployee("AKK", "mar");


        service.addEmployee("BK", "LOG");
        service.addEmployee("KP", "LOG");
        service.addEmployee("JJ", "LOG");

        service.addEmployee("JoS", "KSG");
        service.addEmployee("MoW", "KSG");
        service.addEmployee("MD", "KSG");
        service.addEmployee("XA", "KSG");
        service.addEmployee("XB", "KSG");
        service.addEmployee("XC", "KSG");


    }
}
