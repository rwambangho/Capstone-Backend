package Capstone.Capstone.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Member {


    private Long code;
    private String id;
    private String pw;
    private String username;
    private int age;


    public Member() {
    }

    public Member(String id,String pw,String username, int age) {
        this.id=id;
        this.pw=pw;
        this.username = username;
        this.age = age;
    }

}
