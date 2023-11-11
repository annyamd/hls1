package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = playgroundAvailability)
public class PlaygroundAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playgroundAvailability_id", unique = true, nullable = false)
    private Long playgroundAvailability_id;

    @Column(name = "availability",  nullable = false)
    private Boolean availability;

    @Column(name = "available_from", unique = true, nullable = false)
    private LocalDateTime available_from;

    @Column(name = "available_to", unique = true, nullable = false)
    private LocalDateTime available_to;


    @OneToOne(mappedBy = "playgrounds", fetch = FetchType.LAZY)
    @JoinColumn(name = "playground_id", referencedColumnName = "playground_id")
    private Playground playground;
}
