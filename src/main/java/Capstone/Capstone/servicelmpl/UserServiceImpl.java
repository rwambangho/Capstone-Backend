package Capstone.Capstone.servicelmpl;

import Capstone.Capstone.entity.User;
import Capstone.Capstone.utils.SmsUtil;
import Capstone.Capstone.dto.UserDto;
import Capstone.Capstone.repository.UserRepository;
import Capstone.Capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SmsUtil smsUtil;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,SmsUtil smsUtil) {
        this.userRepository = userRepository;
        this.smsUtil=smsUtil;
    }

    @Override
    public void saveUser(User user) {
        // 저장 전에 중복 이메일 체크 등 필요한 로직 수행 가능
        userRepository.save(user);

    }

    @Override
    public User getUserById(String Id) {
        Optional<User> optionalUser = userRepository.findById(Id);
        return optionalUser.orElse(null);
    }

    @Override
    public void updateUserPassword(String Id, String newPassword) {

        Optional<User> optionalUser = userRepository.findById(Id);

        optionalUser.ifPresent(existingUser -> {
            log.info("userId={} pw={}",Id,newPassword);
            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);
        });
    }

    @Override
    public boolean authenticateUser(String Id, String password) {
        Optional<User> optionalUser = userRepository.findById(Id);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }


    @Override
    public String sendSms(User user) {

            String id = user.getId();
            //수신번호 형태에 맞춰 "-"을 ""로 변환
            String phoneNum = user.getPhoneNumber().replaceAll("-","");

             User foundUser = userRepository.findById(id).orElse(null);


            String receiverEmail = foundUser.getPassword();
            String verificationCode = smsUtil.generateStoreVerificationCode(phoneNum);
            smsUtil.sendOne(phoneNum, verificationCode);

            //인증코드 유효기간 5분 설정
           // redisUtil.setDataExpire(verificationCode, receiverEmail, 60 * 5L);

            return "SMS 전송 성공";
        }

        @Override
        public boolean checkVerificationCode(User user,String verificationCode){
            String phoneNum = user.getPhoneNumber().replaceAll("-","");
            log.info("phoneNum={}",user.getPhoneNumber());
           return smsUtil.checkVerificationCode(phoneNum,verificationCode);

        }

    @Override
    public void checkOutUser() {


    }



}

