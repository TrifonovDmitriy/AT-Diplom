package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ParkingPlace {
    private int id;
    private boolean isCovered;
    private boolean isWarm;
    private int placesCount;
}


