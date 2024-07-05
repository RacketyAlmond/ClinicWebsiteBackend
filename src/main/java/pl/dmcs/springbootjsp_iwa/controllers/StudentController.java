package pl.dmcs.springbootjsp_iwa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.springbootjsp_iwa.model.Prescription;

@Controller
public class StudentController {

    @RequestMapping("/student")
    public String student(Model model) {
        model.addAttribute("message","Simple String from StudentController.");
        Prescription newStudent = new Prescription();
        model.addAttribute("student",newStudent);
        return "student";
    }

    @RequestMapping(value = "/addStudent.html", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Prescription student) {

        return "redirect:student";
    }

}

