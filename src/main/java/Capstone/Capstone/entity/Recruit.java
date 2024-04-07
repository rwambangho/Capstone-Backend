package Capstone.Capstone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idxNum;
    private String title;
    private String contents;
    private String username;
    private Long id;
    private int star;

    @ElementCollection
    private List<String> keywords = new ArrayList<>(); //키워드

    private String destination; //목적지
    private LocalDate departureDate; //출발일자
    private Double departureLatitude; //출발지 위도
    private Double departureLongitude; //출발지 경도
    private boolean isDriverPost; //운전자가 작성한 글인지 아닌지

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; //생성날짜
    @Column(nullable = false)
    private LocalDateTime updatedAt; //수정날짜
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



    public void setIsDriverPost(boolean isDriverPost) {
        this.isDriverPost = isDriverPost;
    }
    public Recruit(int idxNum, String title, String contents, String username, Long id, int star) {
        this.idxNum = idxNum;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.id = id;
        this.star = star;


    }

    public Double getLatitude() {return departureLatitude;}
    public Double getLongitude() {return departureLongitude;}

}

