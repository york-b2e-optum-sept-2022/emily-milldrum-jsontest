package net.yorksolutions.emilymilldrumjavaheaders;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaHeaderController {

    public JavaHeaderController(){

    }

    @GetMapping("/headers")
    public String generateName(){
        return "test";
    }
}
