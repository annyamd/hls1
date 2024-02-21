package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.config.security.utils.JwtUtils;
import ru.itmo.hls1.model.dto.JwtTokenDTO;
import ru.itmo.hls1.model.dto.UserDTO;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> signUp(UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userService.create(userDTO);

        UserDetails userDetails = userService.loadUserByUsername(userDTO.getLogin());

        var jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok().body(new JwtTokenDTO(jwt));
    }

    public ResponseEntity<?> signIn(UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getLogin(),
                userDTO.getPassword()
        ));

        var userDetails = userService.loadUserByUsername(userDTO.getLogin());

        var jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok().body(new JwtTokenDTO(jwt));
    }
}
