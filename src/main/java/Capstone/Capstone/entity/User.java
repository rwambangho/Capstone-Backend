package Capstone.Capstone.entity;

import Capstone.Capstone.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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


    // 생성자, getter, setter 등 필요한 코드 추가
}
