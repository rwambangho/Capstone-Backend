package Capstone.Capstone.entity;

import Capstone.Capstone.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    private String id;

    private String name;
    private Date birthdate;
    private String phoneNumber;

    private String password;
    private String nickname;

    private boolean isDriver; //현재 운전자 모드인지 아닌지
    private String driverLicense; //운전면허증 등록여부

    public void setIsDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }


    // 생성자, getter, setter 등 필요한 코드 추가


    @ManyToMany(mappedBy = "users")
    private Set<ChatRoom> chatRooms = new HashSet<>();


}
