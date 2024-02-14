package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.config.security.wrappers.SecurityUser;
import ru.itmo.hls1.controllers.exceptions.*;
import ru.itmo.hls1.controllers.exceptions.not_found.NotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.PlayerNotFoundException;
import ru.itmo.hls1.controllers.exceptions.not_found.UserNotFoundException;
import ru.itmo.hls1.controllers.exceptions.role.RoleAlreadyGrantedException;
import ru.itmo.hls1.controllers.exceptions.role.RoleRestrictedToGrantManuallyException;
import ru.itmo.hls1.model.dto.RoleDTO;
import ru.itmo.hls1.model.dto.UserDTO;
import ru.itmo.hls1.model.entity.Player;
import ru.itmo.hls1.model.entity.Role;
import ru.itmo.hls1.model.entity.User;
import ru.itmo.hls1.repository.PlayerRepository;
import ru.itmo.hls1.repository.UserRepository;
import ru.itmo.hls1.sevice.util.GeneralService;
import ru.itmo.hls1.sevice.util.Mapper;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends GeneralService<User, UserDTO> implements UserDetailsService {

    private final Mapper<User, UserDTO> mapper = new UserMapper();
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    @Override
    public UserDTO create(UserDTO dto) {
        if (userRepository.findByUsername(dto.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException(dto.getLogin());
        }
        return super.create(dto);
    }

    public void addRole(long id, RoleDTO roleDTO) {
        Role role = roleDTO.getRole();
        if (isRoleNotAllowedGrantManually(role)) {
            throw new RoleRestrictedToGrantManuallyException(role);
        }
        User user = getEntityById(id);
        if (user.getRoles().contains(role)) {
            throw new RoleAlreadyGrantedException(id, role);
        }
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public void removeRole(long id, RoleDTO roleDTO) {
        User user = getEntityById(id);
        Role role = roleDTO.getRole();
        switch (role) {
            case PLAYER -> {
                Player player = playerRepository.findByUser_UserId(id)
                        .orElseThrow(() -> new PlayerNotFoundException("user id = " + id));
                playerRepository.delete(player);
            }
            case TEAM_MANAGER -> {

            }
        }
        user.getRoles().remove(roleDTO.getRole());
        userRepository.save(user);
    }

    public List<RoleDTO> getRoles(long id) {
        User user = getEntityById(id);
        return user.getRoles()
                .stream()
                .map(RoleDTO::new)
                .toList();
    }

    private boolean isRoleNotAllowedGrantManually(Role role) {
        switch (role) {
            case PLAYER, TEAM_MANAGER -> {
                return true;
            }
        }
        return false;
    }

    @Override
    protected NotFoundException getNotFoundIdException(long id) {
        return new UserNotFoundException("id = " + id);
    }

    @Override
    protected Mapper<User, UserDTO> getMapper() {
        return mapper;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepository.findByUsername(username);

        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UserNotFoundException("username = " + username));
    }

    static class UserMapper implements Mapper<User, UserDTO> {

        @Override
        public UserDTO entityToDto(User entity) {
            return new UserDTO(entity.getUserId(), entity.getUsername(), null);
        }

        @Override
        public User dtoToEntity(UserDTO dto) {
            return new User(null, dto.getLogin(), dto.getPassword(), new HashSet<>());
        }
    }

}
