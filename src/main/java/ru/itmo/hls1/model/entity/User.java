package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.itmo.hls1.model.entity.Player;
import ru.itmo.hls1.model.entity.Role;

import java.util.Collection;

/**
 * Table 'user' contains data about registered persons
 */
@Data
@Entity
@Table(name=user)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer user_id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    @OneToOne(mappedBy = "user", orphanRemoval = true, fetch=FetchType.LAZY)
    private Player player;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
