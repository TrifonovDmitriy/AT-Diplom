package dto;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private int age;
    private String firstName;
    private String secondName;
    private String sex;
    private int money;
    private int id;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
