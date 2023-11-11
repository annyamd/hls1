package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name=team)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", unique = true, nullable = false)
    private Integer team_id;

    @Column(name = "team_name", nullable = false)
    private String team_name;

    @Column(name = "team_size", nullable = false)
    private Integer team_size;

    @Column(name = "free_to_join_team", nullable = false)
    private Boolean join_team;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "teams")
    private Collection<Player> players;

    @OneToOne(mappedBy = "team", orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "captain_id", referencedColumnName = "player_id")
    private Player player;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TeamManager manager;
}
