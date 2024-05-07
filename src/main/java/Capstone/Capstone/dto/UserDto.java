package Capstone.Capstone.dto;

import Capstone.Capstone.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter @Setter
public class UserDto {



    private String name;
    private Date birthdate;
    private String phoneNumber;
    private String id;
    private String profileImage;
    private String password;
    private String nickname;
    private int star=0;
    private double avgStar = 0.0;
    private boolean isDriver; //현재 운전자 모드인지 아닌지
    private String driverLicense; //운전면허증 등록여부




    public static UserDto convertToDto(User user){
        UserDto userDto=new UserDto();
        userDto.id= user.getId();
        userDto.nickname=user.getNickname();
        return userDto;
    }

}

