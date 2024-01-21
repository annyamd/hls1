package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;


@Data
@Entity
@Table(name = "playground_availability")
public class PlaygroundAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "available_from", nullable = false)
    private LocalTime availableFrom;

    @Column(name = "available_to", nullable = false)
    private LocalTime availableTo;

    @OneToOne(mappedBy = "playgroundAvailability")
    private Playground playground;
}
