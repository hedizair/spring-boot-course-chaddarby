package com.hzair.springboot.thymeleaddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;



@Controller 
public class HelloWorldController {
    
    // * Controller method that show the form
    @GetMapping("/showForm") // *@GetMapping only support GET request (other will throw an error)
    public String showForm() {
        return "helloworld-form";
    }

    // * Controller method that process the form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }


    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) { // * HttpServletRequest is the request done by the form in showForm, we can read it to get the info from the HTML form
        
        // Read the request parameter from the HTML form
        String studentName = request.getParameter("studentName");
        
        studentName = studentName.toUpperCase();

        String result = "Yo! " + studentName;
        
        // Add the message to the model 
        model.addAttribute("message", result);

        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String letsShoutDude(@RequestParam("studentName") String theName, Model model) { 
        // * @RequestParam allow to directly read the param ("studentName") and binding it into a variable (theName) without using "HttpServletRequest request"
        // * Also, @RequestParm will also check on the request body if the param exist
        String result = "Hey My Friend from v3 " + theName.toUpperCase();
        
        // Add the message to the model 
        model.addAttribute("message", result);

        return "helloworld";
    }
    
    
}
