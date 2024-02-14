package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.config.security.utils.JwtUtils;
import ru.itmo.hls1.config.security.wrappers.SecurityUser;
import ru.itmo.hls1.model.dto.UserDTO;
import ru.itmo.hls1.model.entity.Role;
import ru.itmo.hls1.model.entity.User;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> signUp(UserDTO userDTO) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.PLAYER);

        var user = new User();
        user.setUsername(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roles);

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userService.create(userDTO);

        var jwt = jwtUtils.generateToken(new SecurityUser(user));

        return ResponseEntity.ok().body(jwt);
    }

    public ResponseEntity<?> signIn(UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDTO.getLogin(),
                userDTO.getPassword()
        ));

        var userDetails = userService.loadUserByUsername(userDTO.getLogin());

        var jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok().body(jwt);
    }
}
