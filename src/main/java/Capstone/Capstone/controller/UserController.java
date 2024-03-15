package Capstone.Capstone.controller;

import Capstone.Capstone.dto.UserDto;
import Capstone.Capstone.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kotlinx.serialization.internal.BooleanArrayBuilder;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDTO) {
        userService.saveUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        UserDto userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("id") String Id, @RequestParam("password") String password) {
        boolean isAuthenticated = userService.authenticateUser(Id, password);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/findPw")
    public ResponseEntity<Void> findPassword(@RequestBody UserDto userDTO) {
        userService.sendSms(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/changePassword/{userId}")
    public ResponseEntity<Void> changePassword(@PathVariable String UserId, @RequestParam String newPassword) {
        userService.updateUserPassword(UserId, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendSMS")
    public ResponseEntity<Void> sendSMS(@RequestBody UserDto userDTO){
        userService.sendSms(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/sendSMS/check")
    public boolean checkVerificationCode(@RequestBody UserDto userDTO, @RequestParam("verificationCode")String verificationCode){

       return userService.checkVerificationCode(userDTO,verificationCode);


    }


}
