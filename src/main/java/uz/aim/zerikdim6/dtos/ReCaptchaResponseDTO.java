package uz.aim.zerikdim6.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReCaptchaResponseDTO {
    private boolean success;
    private String challenge_ts;
    private String hostname;

}
