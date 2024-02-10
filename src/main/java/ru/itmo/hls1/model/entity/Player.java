package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Table 'player' contains data about users that are players t.e. user that has a team.
 */


@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer player_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height_cm", nullable = false)
    private Float height;

    @Column(name = "weight_kg", nullable = false)
    private Float weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 1)
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "players_teams",
            joinColumns = @JoinColumn(name = "player_id", referencedColumnName = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "team_id"))
    private List<Team> teams;

    @OneToMany(mappedBy = "player")
    private List<Booking> bookingList;

    public enum Gender {
        M,
        F
    }
}
