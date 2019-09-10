package pl.mirek.xllicensetranslator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@PropertySource("file:./config.ini")
@EnableScheduling
public class ApplicationConfig {

    @Value("${dataUrl}")
    public String dataUrl;

    @Value("${dataFieldPrefix}")
    public String dataFieldPrefix;

    @Value("${testField}")
    public String testField;

    @Value("${testMode}")
    public int testMode;

    public ApplicationConfig() {

    }


}
