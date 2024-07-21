package dto;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class House {
    private int floorCount;
    private int id;
    private int price;
    private List<ParkingPlace> parkingPlaces;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
