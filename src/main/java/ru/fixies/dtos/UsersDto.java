package ru.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.model.Users;

@Data
@NoArgsConstructor
public class UsersDto {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleNames;
    private String email;
    private String phone;

    public UsersDto(Users users) {
        this.id = users.getId();
        this.login = users.getLogin();
        this.password = users.getPassword();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.middleNames = users.getMiddleNames();
        this.email = users.getEmail();
        this.phone = users.getPhone();
    }
}
