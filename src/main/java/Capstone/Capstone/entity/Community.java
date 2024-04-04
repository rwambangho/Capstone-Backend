package Capstone.Capstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
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
    private long clickCount;
    @JsonIgnoreProperties({"community"})
    @OneToMany(mappedBy = "community", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;

    public Community() {

    }

    // 생성자, getter, setter 등 필요한 코드 추가
}
