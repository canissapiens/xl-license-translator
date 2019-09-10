package pl.mirek.xllicensetranslator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import pl.mirek.xllicensetranslator.components.Initialize;
import pl.mirek.xllicensetranslator.config.ApplicationConfig;
import pl.mirek.xllicensetranslator.service.FileService;
import pl.mirek.xllicensetranslator.service.OrganizationService;

@SpringBootApplication
public class XlLicenseTranslatorApplication {

    private Initialize initialize;

    @Autowired
    public XlLicenseTranslatorApplication(Initialize initialize) {
        this.initialize = initialize;
    }

    public static void main(String[] args) {
        SpringApplication.run(XlLicenseTranslatorApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationInit() {
        initialize.initOrganization();
    }
}
