package com.hzair.springboot.thymeleaddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hzair.springboot.thymeleaddemo.model.Student;

@Controller
public class StudentController {

    @Value("${countries}") // * Inject countries list from the application.properties files, splitted by //
                           // comma
    private List<String> countries;

    @Value("${programmingLanguages}")
    private List<String> programmingLanguages;

    @Value("${systems}")
    private List<String> operatingSystems;

    @GetMapping("/showStudentForm")
    public String showForm(Model model) {

        // create student object

        Student theStudent = new Student();

        // retrieve countries from the application.properties files

        // add student object to the model
        model.addAttribute("theStudent", theStudent);
        // add the list of countries to the model
        model.addAttribute("countries", this.countries);
        // add the list of programming languages to the model
        model.addAttribute("programmingLanguages", this.programmingLanguages);
        // add the list of operating systems to the model
        model.addAttribute("operatingSystems", this.operatingSystems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("theStudent") Student theStudent) {
        // * ModelAttribute will bind the request parameter into an Java/Bean object
        // * The parameter need to be the same as the th:object indicated into the html
        // form, so "theStudent" here

        System.out.println("The student: " + theStudent.getFirstName() + " " + theStudent.getLastName());
        System.out.println("His favorite Language: " + theStudent.getFavoriteLanguage());
        System.out.println("His favorite Systems: " + theStudent.getFavoriteSystems());
        

        return "student-confirmation";
    }

}
