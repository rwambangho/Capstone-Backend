package Capstone.Capstone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String nickName;
    private double time;
    private String image;
    private Long like_count;


    public Community(String title, String content, double time) {
        this.title = title;
        this.content = content;
        this.time = time;

    }

    public Community() {

    }
}
