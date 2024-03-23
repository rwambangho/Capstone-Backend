package Capstone.Capstone.service;
import Capstone.Capstone.entity.User;

public interface UserService {
    void saveUser(User user);

    User getUserById(String Id);
    void updateUserPassword(String Id, String newPassword);
    boolean authenticateUser(String Id, String password);
    String sendSms(User user);

    boolean checkVerificationCode(User user, String verificationCode);

    void checkOutUser();
}
