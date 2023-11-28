package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

/**
 * Table 'player' contains data about users that are players t.e. user that has a team.
 */


@Data
@Entity
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer player_id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Float height_cm ;

    @Column(nullable = false)
    private Float weight_kg ;

    @Column(nullable = false, length = 1)
    private String gender;

//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private User user;
//
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name = "player_team", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
//    private Collection<Team> teams;
}
