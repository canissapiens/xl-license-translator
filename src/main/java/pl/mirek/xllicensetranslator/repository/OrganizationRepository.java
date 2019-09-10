package pl.mirek.xllicensetranslator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import pl.mirek.xllicensetranslator.model.Organization;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrganizationRepository {

    private Organization organization;
    private Map<String, String> userMapping = new HashMap<>();

    public OrganizationRepository() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Map<String, String> getUserMapping() {
        return userMapping;
    }

    public void setUserMapping(Map<String, String> userMapping) {
        this.userMapping = userMapping;
    }

}
