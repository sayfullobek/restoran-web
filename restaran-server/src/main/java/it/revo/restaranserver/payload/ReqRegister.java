package it.revo.restaranserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRegister {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    private String prePassword;
}
