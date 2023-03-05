package entity;

import lombok.*;

import java.sql.Date;


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
}
