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
    private String password;
    private String nickname;





    public static UserDto convertToDto(User user){
        UserDto userDto=new UserDto();
        userDto.id= user.getId();
        userDto.nickname=user.getNickname();
        return userDto;
    }

}

