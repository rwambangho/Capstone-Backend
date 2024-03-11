package Capstone.Capstone.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@RestController
public class TestController {
    @GetMapping("/")
    public String test(){return "First Controller";}

    }


