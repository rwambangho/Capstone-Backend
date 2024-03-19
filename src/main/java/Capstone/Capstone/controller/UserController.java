package Capstone.Capstone.controller;


import Capstone.Capstone.entity.User;
import Capstone.Capstone.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name="User API",description = "User API입니다.")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signUp")
    @Tag(name="User API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "유저 셍성")
    })
    @Operation(summary = "회원가입",description = "User 정보를 저장합니다.")
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{userId}")
    @Tag(name="User API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 확인"),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 유저"),
    })
    @Operation(summary = "회원 찾기",description = "id를 기반으로 user를 찾습니다.")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);
        if(user!=null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    @Tag(name="User API")
    @Operation(summary = "로그인",description = "id와 password를 기반으로 로그인합니다.")
    public ResponseEntity<String> login(@RequestParam("id") String Id, @RequestParam("password") String password) {
        boolean isAuthenticated = userService.authenticateUser(Id, password);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/findPw")
    @Tag(name="User API")
    @Operation(summary = "비밀번호 찾기",description = "비밀번호를 찾습니다.")
    public ResponseEntity<Void> findPassword(@RequestBody User user) {
        userService.sendSms(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/changePassword/{userId}")
    @Tag(name="User API")
    @Operation(summary = "비밀번호 변경",description ="password를 변경합니다")
    public ResponseEntity<Void> changePassword(@PathVariable("userId") String userId, @RequestParam String newPassword) {
        userService.updateUserPassword(userId, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendSMS")
    @Tag(name="User API")
    @Operation(summary = "sms 발송",description = "userDto의 번호로 인증 문자를 보냅니다")
    public ResponseEntity<Void> sendSMS(@RequestBody User user){
        userService.sendSms(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/sendSMS/check")
    @Tag(name="User API")
    @Operation(summary = "인증문자 확인",description = "입력한 번호가 인증 문자가 맞는지 확인합니다.")
    public boolean checkVerificationCode(@RequestBody User user, @RequestParam("verificationCode")String verificationCode){

       return userService.checkVerificationCode(user,verificationCode);


    }


}
