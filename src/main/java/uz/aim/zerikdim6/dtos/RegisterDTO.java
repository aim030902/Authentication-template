package uz.aim.zerikdim6.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterDTO {
    private String fullName;
    private String username;
    private String password;
    private String email;
}
