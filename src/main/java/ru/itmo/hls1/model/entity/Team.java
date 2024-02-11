package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_size", nullable = false)
    private Long teamSize;

    @Column(name = "is_free_to_join", nullable = false)
    private Boolean isFreeToJoin;

    @ManyToMany(mappedBy = "teams")
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private TeamManager manager;

    @OneToMany(mappedBy = "team")
    private List<Booking> bookingList;
}
