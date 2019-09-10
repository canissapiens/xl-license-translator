package pl.mirek.xllicensetranslator.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.mirek.xllicensetranslator.config.ApplicationConfig;
import pl.mirek.xllicensetranslator.model.Division;
import pl.mirek.xllicensetranslator.service.FileService;
import pl.mirek.xllicensetranslator.service.OrganizationService;
import pl.mirek.xllicensetranslator.service.UtilityService;

import java.io.File;

@Component
public class Initialize {

    private Environment env;
    private ApplicationConfig config;
    private OrganizationService organizationService;
    private FileService fileService;
    private UtilityService utilityService;

    @Autowired
    public Initialize(Environment env,
                      ApplicationConfig config,
                      OrganizationService organizationService,
                      FileService fileService,
                      UtilityService utilityService
    ) {
        this.env = env;
        this.config = config;
        this.organizationService = organizationService;
        this.fileService = fileService;
        this.utilityService = utilityService;
    }

    public void initOrganization() {
        String filename = env.getProperty("oganization.config.file");
        File file = new File(filename);
        if (file.exists()) {
            organizationService.setOrganizationFromJsonString(
                    fileService.readStringFromFile(filename));
            organizationService.getDivisions().add(new Division("GENERAL"));
        } else {
            utilityService.initOrganization();
            fileService.writeStringToFile(filename, organizationService.getOrganizationInJsonFormat());
            organizationService.getDivisions().add(new Division("GENERAL"));
        }
        if (config.testMode != 0) {
            System.out.println(organizationService.getUserMap());
        }
        organizationService.prepareTemporaryLicenseUtilization();
        organizationService.setLicenseUtilization();
    }


}
