package pl.mirek.xllicensetranslator.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirek.xllicensetranslator.config.ApplicationConfig;

import java.io.File;
import java.io.IOException;

@Service
public class PageContent {

    private ApplicationConfig config;

    @Autowired
    public PageContent(ApplicationConfig config) {
        this.config = config;
    }

    public Document getPageContent() {
        return getPageContentFromInternet();
    }

    public Document getPageContentFromInternet() {
        try {
            return Jsoup.connect(config.dataUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Brak połączenia");
        }
        return null;
    }

    // for testing
    public Document getPageContentFromFile() {
        File file = new File("input.html");
        try {
            return Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
