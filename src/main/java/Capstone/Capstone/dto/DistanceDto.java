package Capstone.Capstone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistanceDto {
    private double  departureX;
    private double  departurey;

    private double arrivalX;
    private double arrivaly;

    private double currentX;
    private double currentY;

    private double time;
}
