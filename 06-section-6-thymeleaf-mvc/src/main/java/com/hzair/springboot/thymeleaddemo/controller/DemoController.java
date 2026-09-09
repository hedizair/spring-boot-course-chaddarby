package com.hzair.springboot.thymeleaddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller 
public class DemoController {

    // create a mapping for "/hello"
    @RequestMapping("/hello") // * @RequestMapping handle all the http method : GET, POST, PUT ...
    public String sayHello(Model theModel) { // Model is used to pass data to the view, like a container (thymeleaf in our case)

        theModel.addAttribute("theDate", java.time.LocalDateTime.now());
        return "helloword"; // return the name of the view (helloword.html)
    }
    

}
