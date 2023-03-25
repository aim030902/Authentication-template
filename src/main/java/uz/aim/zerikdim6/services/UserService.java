package uz.aim.zerikdim6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.aim.zerikdim6.domains.User;
import uz.aim.zerikdim6.dtos.ApiResponse;
import uz.aim.zerikdim6.dtos.LoginDTO;
import uz.aim.zerikdim6.dtos.RegisterDTO;
import uz.aim.zerikdim6.mapper.UserMapper;
import uz.aim.zerikdim6.repository.UserRepository;
import uz.aim.zerikdim6.validator.UserValidator;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    
    public ApiResponse register(RegisterDTO dto) {
        userValidator.validatorOnRegister(dto);
        User createdUser = UserMapper.toEntityFromRegisterDTO(dto);
        createdUser.setPassword(passwordEncoder.encode(createdUser.getPassword()));
        User savedUser = userRepository.save(createdUser);
        return new ApiResponse("User successfully registered", savedUser, true);
    }

    public ApiResponse login(LoginDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        User user = (User) authenticate.getPrincipal();
        return new ApiResponse("User successfully login", user, true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new uz.aim.zerikdim6.configs.UserDetails(optionalUser.get());
    }
}
