package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name=playground)
public class Playground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playground_id", unique = true, nullable = false)
    private Integer playground_id;

    @Column(name = "playground_name", nullable = false)
    private String playgroundName;

    @Column(name = "location", nullable = false)
    private String locationList;


    @OneToMany(mappedBy = "playgrounds", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", referencedColumnName = "sport_id")
    private Collection<Sport> sports;

    @OneToOne(mappedBy = "playgrounds", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private PlaygroundAvailability playgroundAvailability;

}
