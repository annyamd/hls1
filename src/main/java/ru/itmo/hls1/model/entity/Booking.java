package ru.itmo.hls1.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name=booking)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id", unique = true, nullable = false)
    private Long booking_id;

    @Column(name = "booking_start_time", unique = true, nullable = false)
    private LocalDateTime startTime;

    @Column(name = "booking_end_time", unique = true, nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "teamManager_id", referencedColumnName = "teamManager_id")
    private TeamManager teamManager;


    @OneToOne(mappedBy = "booking", orphanRemoval = true)
    @JoinColumn(name = "playgroundAvailability_id", referencedColumnName = "playgroundAvailability_id")
    private PlaygroundAvailability playgroundAvailability;

}
