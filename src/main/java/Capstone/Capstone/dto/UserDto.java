package Capstone.Capstone.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter @Setter
public class UserDto {



    private String name;
    private Date birthdate;
    private String phoneNumber;
    private String id;
    private String password;
    private String nickname;
    private boolean isDriver; //현재 운전자 모드인지 아닌지
    private String driverLicense; //운전면허증 등록여부




}
