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

    private String name;

    private double time;

    private double startLocation[];

    private double endLocation[];


    public Community(String title, String content, String name, double time, double[] startLocation, double[] endLocation) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.time = time;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }
}
