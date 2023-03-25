package uz.aim.zerikdim6.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginDTO {
    private String username;
    private String password;
}
