package Capstone.Capstone.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RecruitDto {
    private int idxNum;
    private String title;
    private String contents;
    private String username;
    private Long id;
    private int star;
    private String destination;
    private LocalDate departureDate;
    private Double departureLatitude;
    private Double departureLongitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDriverPost; //운전자가 작성한 글인지 아닌지
}
