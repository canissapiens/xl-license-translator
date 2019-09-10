package pl.mirek.xllicensetranslator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mirek.xllicensetranslator.components.Initialize;
import pl.mirek.xllicensetranslator.dto.DisplayDataDto;
import pl.mirek.xllicensetranslator.service.OrganizationService;

@Controller
public class MainController {

    private Initialize initialize;
    private OrganizationService organizationService;

    @Autowired
    public MainController(Initialize initialize, OrganizationService organizationService) {
        this.initialize = initialize;
        this.organizationService = organizationService;
    }

    @GetMapping(value = "/")
    public String displayResultSheet(Model model) {
        model.addAttribute("output", organizationService.getDisplayData());
        return "license";
    }

    @GetMapping(value = "/json")
    @ResponseBody
    public DisplayDataDto getdata() {
        return organizationService.getDisplayData();
    }

    @GetMapping(value = "/json-reload")
    public String reloadOrganizationConfig() {
        initialize.initOrganization();
        return "redirect:/";
    }
}
