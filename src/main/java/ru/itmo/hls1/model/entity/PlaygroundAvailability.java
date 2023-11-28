package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "playgroundAvailability")
public class PlaygroundAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long playgroundAvailability_id;

    @Column(nullable = false)
    private Boolean availability;

    @Column(unique = true, nullable = false)
    private LocalDateTime available_from;

    @Column(unique = true, nullable = false)
    private LocalDateTime available_to;

    @OneToOne
    @JoinColumn(name = "playground_id", referencedColumnName = "playground_id")
    private Playground playground;
}
