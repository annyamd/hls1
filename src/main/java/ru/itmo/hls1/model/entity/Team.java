package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer team_id;

    @Column(nullable = false)
    private String team_name;

    @Column(nullable = false)
    private Integer team_size;

    @Column(nullable = false)
    private Boolean is_free_to_join;

//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "teams")
//    private Collection<Player> players;
//
//    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "captain_id", referencedColumnName = "player_id")
//    private Player player;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private TeamManager manager;
}
