package Capstone.Capstone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ComunityDto {

    private Long id;
    private String title;
    private String content;

    private String name;

    private double time;

    private double startLocation[];

    private double endLocation[];

    public ComunityDto(String title, String content, String name, double time, double[] startLocation, double[] endLocation) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.time = time;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }
}
