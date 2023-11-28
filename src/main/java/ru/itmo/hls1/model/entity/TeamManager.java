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
    @Column(unique = true, nullable = false)
    private Integer teamManager_id;

//    @OneToOne
//    @JoinColumn(referencedColumnName = "player_id")
//    private Player player;
//
//    @OneToMany
//    @JoinColumn(referencedColumnName = "team_id")
//    private Collection<Team> teams;
}
