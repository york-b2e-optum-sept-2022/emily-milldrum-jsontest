package net.yorksolutions.emilymilldrumjavaheaders;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class JavaHeaderController {
    private final JavaHeaderService javaHeaderService;
    public JavaHeaderController(JavaHeaderService javaHeaderService) {
        //this.request = request;
        this.javaHeaderService = javaHeaderService;
    }

    @GetMapping("/headers")
    public Map<String, String> generateName() {
        //return this.javaHeaderService.ip();
        return this.javaHeaderService.getRequestHeadersInMap();
    }

    @GetMapping("/ipaddress")
    public String ipAd() {
        //return this.javaHeaderService.ip();
        return this.javaHeaderService.getClientIp();
    }

    @GetMapping("/date")
    public String date() {
        //return this.javaHeaderService.ip()
        return this.javaHeaderService.date();
    }


}
