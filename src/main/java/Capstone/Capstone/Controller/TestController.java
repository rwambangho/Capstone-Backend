package Capstone.Capstone.Controller;

import Capstone.Capstone.Selenium;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@RestController
public class TestController {
    @GetMapping("/")
    public String test(){return "First Controller";}

    @GetMapping("/showMe")
    public List<String> hello(){
        return Arrays.asList("A","B");
    }

    @GetMapping("/test")
    public String SeleniumController() throws IOException {
        Selenium sel=new Selenium();
        String url="https://framer.com";
    sel.useDriver(url);

    return "main";
    }

}
