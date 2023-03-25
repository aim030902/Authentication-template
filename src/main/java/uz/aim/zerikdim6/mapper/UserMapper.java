package uz.aim.zerikdim6.mapper;

import uz.aim.zerikdim6.domains.User;
import uz.aim.zerikdim6.dtos.RegisterDTO;

public interface UserMapper {
    static User toEntityFromRegisterDTO(RegisterDTO dto) {
        return User
                .builder()
                .fullName(dto.getFullName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
