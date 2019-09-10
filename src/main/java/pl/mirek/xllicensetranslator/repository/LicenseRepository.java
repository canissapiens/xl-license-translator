package pl.mirek.xllicensetranslator.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LicenseRepository {

    private Map<String, Integer> licenseUtilization = new HashMap<>();
    private List<String> currentActiveUsers;
    public LicenseRepository() {

    }


    public Map<String, Integer> getLicenseUtilization() {
        return licenseUtilization;
    }

    public void setLicenseUtilization(Map<String, Integer> licenseUtilization) {
        this.licenseUtilization = licenseUtilization;
    }

    public List<String> getCurrentActiveUsers() {
        return currentActiveUsers;
    }

    public void setCurrentActiveUsers(List<String> currentActiveUsers) {
        this.currentActiveUsers = currentActiveUsers;
    }
}

