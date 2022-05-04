package ru.fixies.fixies.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fixies.fixies.model.User;


@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleNames;
    private String email;
    private String phone;

    public UserDto(User users) {
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
