package entity;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String username;
    private String nationalCode;
    private Date birthday;
    private String password;

    public User(String username, String nationalCode, LocalDate birthday, String password) {
    }
}
