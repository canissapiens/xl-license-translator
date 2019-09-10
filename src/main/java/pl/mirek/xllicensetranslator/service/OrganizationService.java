package pl.mirek.xllicensetranslator.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.mirek.xllicensetranslator.config.ApplicationConfig;
import pl.mirek.xllicensetranslator.dto.DisplayDataDto;
import pl.mirek.xllicensetranslator.dto.SimplyDivisionDto;
import pl.mirek.xllicensetranslator.model.Division;
import pl.mirek.xllicensetranslator.model.Employee;
import pl.mirek.xllicensetranslator.model.Organization;
import pl.mirek.xllicensetranslator.repository.LicenseRepository;
import pl.mirek.xllicensetranslator.repository.OrganizationRepository;

import java.util.*;

@Service
public class OrganizationService {

    private Environment env;
    private ApplicationConfig config;
    private OrganizationRepository organizationRepository;
    private LicenseRepository licenseRepository;
    private Gson gson;
    private Map<String, Integer> temporaryLicenseUtilization;
    private List<String> temporaryCurrentActiveUsers;
    private FileService fileService;


    @Autowired
    public OrganizationService(ApplicationConfig config,
                               OrganizationRepository organizationRepository,
                               LicenseRepository licenseRepository,
                               Gson gson,
                               FileService fileService) {
        this.config = config;
        this.organizationRepository = organizationRepository;
        this.licenseRepository = licenseRepository;
        this.gson = gson;
        this.fileService = fileService;
    }

    public void addEmployee(String acronym, String divisionName) {
        acronym = acronym.toUpperCase();
        divisionName = divisionName.toUpperCase();
        Employee employee = new Employee(acronym);
        Division division;
        if ((division = getDivisionByName(divisionName)) != null) {
            division.getEmployees().add(employee);
        } else {
            division = new Division(divisionName);
            division.getEmployees().add(employee);

            this.getDivisions().add(division);
            this.setZeroUtilizationForNewDivision(divisionName);
        }
        this.makeUserMap();
    }

    public boolean isDivision(String division) {
        for (Division d : this.getDivisions()) {
            if (d.getName().equalsIgnoreCase(division)) {
                return true;
            }
        }
        return false;
    }

    public Division getDivisionByName(String division) {
        for (Division d : this.getDivisions()) {
            if (d.getName().equalsIgnoreCase(division))
                return d;
        }
        return null;
    }

    public Division getDivisionByUserName(String userName) {
        return getDivisionByName(getUserMap().get(userName));
    }

    public Set<Division> getDivisions() {
        return this.organizationRepository.getOrganization().getDivisions();
    }

    public void makeUserMap() {
        Map<String, String> userMap = new HashMap<>();
        Set<Division> divisions = this.getDivisions();
        if (divisions.size() > 0) {
            for (Division d : divisions) {
                if (d.getEmployees().size() > 0) {
                    for (Employee e : d.getEmployees()) {
                        userMap.put(e.getAcronym(), d.getName());
                    }
                }
            }
        } // if
        organizationRepository.setUserMapping(userMap);
    }

    public Map<String, String> getUserMap() {
        return organizationRepository.getUserMapping();
    }

    public Organization getOrganization() {
        return organizationRepository.getOrganization();
    }

    public String getOrganizationInJsonFormat() {
        return gson.toJson(this.getOrganization());
    }

    public void setOrganizationFromJsonString(String json) {

        organizationRepository.setOrganization(gson.fromJson(json, Organization.class));

        this.makeUserMap();
    }

    public void setZeroUtilizationForNewDivision(String divisionName) {
        this.licenseRepository.getLicenseUtilization().put(divisionName, 0);
    }

    public void prepareTemporaryLicenseUtilization() {
        this.temporaryLicenseUtilization = new HashMap<>();
        for (Division d : this.getDivisions()) {
            temporaryLicenseUtilization.put(d.getName(), 0);
        }
        temporaryLicenseUtilization.put("GENERAL", 0);
    }

    public void prepareTemporaryCurrentActiveUsers() {
        temporaryCurrentActiveUsers = new ArrayList<>();
    }

    private int calculateSumOfLicenseUtilization() {
        Integer suma = 0;
        for (Map.Entry<String, Integer> entry : temporaryLicenseUtilization.entrySet()) {
            suma += entry.getValue();
        }
        return suma;
    }

    public void setLicenseUtilization() {
        licenseRepository.setLicenseUtilization(this.temporaryLicenseUtilization);
        if (config.testMode != 0) {
            System.out.println(temporaryLicenseUtilization + ", suma: " + calculateSumOfLicenseUtilization());
        }
    }

    public void setCurrentActiveUsers() {
        licenseRepository.setCurrentActiveUsers(this.temporaryCurrentActiveUsers);
    }

    public void addLicenseUtilizationForUser(String userName) {
        String divisionName = null;
        if (userName == "Administrator") {
            divisionName = "ADMINISTRATOR";
        } else {
            divisionName = this.getUserMap().get(userName);
        }

        if (divisionName == null) {
            // System.out.println("---> added new Employee: " + userName);
            divisionName = "GENERAL";
            addEmployee(userName, divisionName);
        }
        temporaryLicenseUtilization.put(divisionName, temporaryLicenseUtilization.get(divisionName) + 1);
        temporaryCurrentActiveUsers.add(userName);
    }

    public Map<String, Integer> getFinalLicenseUtilization() {
        return licenseRepository.getLicenseUtilization();
    }

    public DisplayDataDto getDisplayData() {
        SimplyDivisionDto simplyDivisionDto = null;
        int temporaryUtilization;
        String temporaryDivisionName = null;
        DisplayDataDto displayDataDto = new DisplayDataDto(String.valueOf(calculateSumOfLicenseUtilization()));
        for (Map.Entry<String, Integer> entry : licenseRepository.getLicenseUtilization().entrySet()) {
            temporaryUtilization = 0;
            temporaryDivisionName = entry.getKey();
            simplyDivisionDto = new SimplyDivisionDto(temporaryDivisionName);
            for (String activeUser : licenseRepository.getCurrentActiveUsers()) {
                if (getUserMap().get(activeUser).equals(temporaryDivisionName)) {
                    simplyDivisionDto.activeLicenses.add(activeUser);
                    temporaryUtilization++;
                }
            }

            if (temporaryUtilization != 0) {
                simplyDivisionDto.licenseUtilization = temporaryUtilization;
                simplyDivisionDto.licenseLimit = getDivisionByName(temporaryDivisionName).getLimit();
                if (simplyDivisionDto.licenseUtilization > simplyDivisionDto.licenseLimit) {
                    simplyDivisionDto.colorclass = "row-warning";
                }
                displayDataDto.divisions.add(simplyDivisionDto);
            }
        }
        return displayDataDto;
    } //
}
