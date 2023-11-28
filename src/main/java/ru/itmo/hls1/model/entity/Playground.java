package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name="playground")
public class Playground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playground_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "playground_name", nullable = false)
    private String playgroundName;

    @Column(name = "location", nullable = false)
    private String location;


//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "playground")
    private Collection<Sport> sports;

    @OneToOne(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlaygroundAvailability playgroundAvailability;

}
