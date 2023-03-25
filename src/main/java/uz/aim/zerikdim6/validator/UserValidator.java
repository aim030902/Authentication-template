package uz.aim.zerikdim6.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.zerikdim6.dtos.RegisterDTO;
import uz.aim.zerikdim6.exception.ConflictException;
import uz.aim.zerikdim6.repository.UserRepository;

@Component
public class UserValidator {
    @Autowired
    private UserRepository userRepository;
    public void validatorOnRegister(RegisterDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ConflictException("This is username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("This is email already exists");
        }
    }
}
