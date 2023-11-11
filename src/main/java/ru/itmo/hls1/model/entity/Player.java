package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

/**
 * Table 'player' contains data about users that are players t.e. user that has a team.
 */


@Data
@Entity
@Table(name=player)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", unique = true, nullable = false)
    private Integer player_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height_cm", nullable = false)
    private Float height_cm ;

    @Column(name = "weight_kg", nullable = false)
    private Float weight_kg ;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "player_team", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Collection<Team> teams;
}
