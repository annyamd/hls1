package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import ru.itmo.hls1.model.entity.User;

import java.util.Collection;

/**
 * Table 'ROLE' contains data about roles: user-moderator-administrator
 */
@Entity
@Table(name=role)

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<User> users;
}
