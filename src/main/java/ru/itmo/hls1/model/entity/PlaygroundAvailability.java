package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "playground_availability")
public class PlaygroundAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "available_from")
    private LocalTime availableFrom;

    @Column(name = "available_to")
    private LocalTime availableTo;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToOne(mappedBy = "playgroundAvailability")
    private Playground playground;
}
