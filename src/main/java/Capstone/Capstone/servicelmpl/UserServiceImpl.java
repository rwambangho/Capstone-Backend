package Capstone.Capstone.servicelmpl;

import Capstone.Capstone.SmsUtil;
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
    public void saveUser(UserDto userDTO) {
        // 저장 전에 중복 이메일 체크 등 필요한 로직 수행 가능
        userRepository.save(userDTO);

    }

    @Override
    public UserDto getUserById(String Id) {
        return userRepository.findById(Id);
    }

    @Override
    public void updateUserPassword(String userId, String newPassword) {
        userRepository.updatePassword(userId, newPassword);
    }

    @Override
    public boolean authenticateUser(String Id, String password) {
        UserDto userDTO = userRepository.findById(Id);
        return userDTO != null && userDTO.getPassword().equals(password);
    }


    @Override
    public String sendSms(UserDto userDTO) {

            String id = userDTO.getId();
            //수신번호 형태에 맞춰 "-"을 ""로 변환
            String phoneNum = userDTO.getPhoneNumber().replaceAll("-","");

            UserDto foundUser = userRepository.findById(id);


            String receiverEmail = foundUser.getPassword();
            String verificationCode = smsUtil.generateStoreVerificationCode(phoneNum);
            smsUtil.sendOne(phoneNum, verificationCode);

            //인증코드 유효기간 5분 설정
           // redisUtil.setDataExpire(verificationCode, receiverEmail, 60 * 5L);

            return "SMS 전송 성공";
        }

        @Override
        public boolean checkVerificationCode(UserDto userDTO,String verificationCode){
            String phoneNum = userDTO.getPhoneNumber().replaceAll("-","");
            log.info("phoneNum={}",userDTO.getPhoneNumber());
           return smsUtil.checkVerificationCode(phoneNum,verificationCode);

        }

}

