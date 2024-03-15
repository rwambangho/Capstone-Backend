package Capstone.Capstone.service;
import Capstone.Capstone.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDTO);

    UserDto getUserById(String Id);
    void updateUserPassword(String Id, String newPassword);
    boolean authenticateUser(String Id, String password);
    String sendSms(UserDto UserDTO);

    boolean checkVerificationCode(UserDto userDTO,String verificationCode);
}
