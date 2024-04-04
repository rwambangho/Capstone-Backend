package Capstone.Capstone.service;
import Capstone.Capstone.dto.UserDto;
import Capstone.Capstone.entity.User;

public interface UserService {
    void saveUser(User user);

    User getUserById(String Id);
    void updateUserPassword(String Id, String newPassword);
    boolean authenticateUser(String Id, String password);
    String sendSms(User user);

    boolean checkVerificationCode(User user, String verificationCode);

    public User convertToEntity(UserDto userDto);

    public  UserDto convertToDto(User user);

    void checkOutUser();
}
