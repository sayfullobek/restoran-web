package it.revo.restaranserver.payload;

import it.revo.restaranserver.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLogin {
    private User user;
    private ResToken resToken;
}
