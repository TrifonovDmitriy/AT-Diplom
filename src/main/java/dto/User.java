package dto;

import lombok.Builder;

@Builder
public class User {
    private int age;
    private String firstName;
    private String lastName;
    private String sex;
    private int money;
}
