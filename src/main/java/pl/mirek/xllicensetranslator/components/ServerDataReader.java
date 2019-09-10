package pl.mirek.xllicensetranslator.components;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.mirek.xllicensetranslator.config.ApplicationConfig;
import pl.mirek.xllicensetranslator.service.OrganizationService;
import pl.mirek.xllicensetranslator.service.PageContent;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ServerDataReader {

    private ApplicationConfig config;
    private OrganizationService organizationService;
    private PageContent pageContent;

    @Autowired
    public ServerDataReader(ApplicationConfig config, OrganizationService service, PageContent pageContent) {
        this.config = config;
        this.organizationService = service;
        this.pageContent = pageContent;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = 5000L)
    private void readDataFromInternet() {
        try {
            Document myPage = pageContent.getPageContent();
            Elements elements = myPage.getElementsByTag("td");
            selectUsers(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractUser(String line) {
        // (CSI-TS-NEW\\)(.+):(.*)
        String input = "(" + config.dataFieldPrefix + "\\\\)(.+):(.*)";
        Pattern pattern = Pattern.compile(input);
        Matcher matcher = pattern.matcher(line);
        matcher.matches();
        return matcher.group(2).toUpperCase();
    }

    public void selectUsers(Elements elements) {
        Set<String> tmpLoggedUsers = new HashSet<>();

        String tmpUser;
        boolean isValidData = false;
        organizationService.prepareTemporaryLicenseUtilization();
        organizationService.prepareTemporaryCurrentActiveUsers();
        for (Element element : elements) {
            {
                if (!isValidData) {
                    if (element.ownText().contains("XL Licencja stanowiskowa")) {
                        isValidData = true;
                    }
                    continue;
                }
                if (element.ownText().contains("XL Administracja")) {
                    break;
                }
                tmpUser = element.ownText();

                do {
                    if (tmpUser.toLowerCase().contains("administrator")) {
                        organizationService.addLicenseUtilizationForUser("Administrator");
                        continue;
                    }

                    if (tmpUser.contains(config.dataFieldPrefix)) {
                        organizationService.addLicenseUtilizationForUser(extractUser(tmpUser));
                    }
                } while (false);
            }
        } // for
        organizationService.setLicenseUtilization();
        organizationService.setCurrentActiveUsers();
    }
}

