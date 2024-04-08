package Capstone.Capstone.entity;


import Capstone.Capstone.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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


    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @ManyToMany(mappedBy = "users")
    private Set<ChatRoom> chatRooms = new HashSet<>();

}
