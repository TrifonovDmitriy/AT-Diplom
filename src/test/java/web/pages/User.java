package web.pages;

import lombok.Builder;

@Builder
public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private int money;

}
