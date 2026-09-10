package com.hzair.springboot.thymeleaddemo.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hzair.springboot.thymeleaddemo.model.Customer;

import jakarta.validation.Valid;

@Controller
public class CustomerController {



    // * function that will be executed for every web request calling to the controller (pre processing so).
    // * It will be used to "formate" the data passed to the request, as trimming data for exemple.
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // * transform empty string to null

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/processCustomerForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResults, Model model) {
        // * @Valid = Tell Spring MVC to perform validation
        // * @ModelAttribute read the "customer" attribute to make the validation indicated in the class (ex: @Size)
        // * BindingResult = The result of the validation (if there is errors or if this is valid)


        System.out.println("Last Name: |"+theCustomer.getLastName()+"|");

        System.out.println("Binding result: " + theBindingResults.toString());

        System.out.println("\n\n\n\n");

        if (theBindingResults.hasErrors()) {
            return "customer-form";
        }
        return "customer-confirmation";
    }

}
