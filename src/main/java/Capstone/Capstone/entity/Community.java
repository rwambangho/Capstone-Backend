package Capstone.Capstone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime time;
    private String image;
    private Long likeCount;


    public Community(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public Community() {

    }
}
