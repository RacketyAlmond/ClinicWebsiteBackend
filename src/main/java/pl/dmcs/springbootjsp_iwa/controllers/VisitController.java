package pl.dmcs.springbootjsp_iwa.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.springbootjsp_iwa.model.Visit;


@RestController
@CrossOrigin(origins = "http://localhost:4200") // This can be removed if global configuration is working

public class VisitController {

    @RequestMapping("/visit")
    public String visit(Model model) {
        model.addAttribute("message","Simple String from VisitController.");
        Visit newVisit = new Visit();
        model.addAttribute("student",newVisit);
        return "visit";
    }

    @RequestMapping(value = "/addVisit.html", method = RequestMethod.POST)
    public String addVisit(@ModelAttribute("visit") Visit visit) {



        return "redirect:visit";
    }

}

