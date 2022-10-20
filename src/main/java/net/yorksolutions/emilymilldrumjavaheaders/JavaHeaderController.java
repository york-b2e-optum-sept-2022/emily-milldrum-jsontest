package net.yorksolutions.emilymilldrumjavaheaders;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
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
        return this.javaHeaderService.getClientIp();
    }

    @GetMapping("/date")
    public String date() {
        return this.javaHeaderService.date();
    }

    @GetMapping("/md5/{input}")
    public String md5(@PathVariable String input) throws NoSuchAlgorithmException {
        return this.javaHeaderService.md5(input);
    }

    @GetMapping("/jsontest/{jsonInc}")
    public boolean jsonTest(@PathVariable String jsonInc) throws IllegalArgumentException{
        return this.javaHeaderService.jsonTest(jsonInc);
    }


}
