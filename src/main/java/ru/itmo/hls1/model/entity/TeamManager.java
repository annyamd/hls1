package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "teamManager")
public class TeamManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_manager_id", unique = true, nullable = false)
    private Integer teamManager_id;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @OneToMany
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Collection<Team> teams;
}
