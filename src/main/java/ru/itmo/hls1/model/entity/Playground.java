package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name="playground")
public class Playground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playground_id")
    private long id;

    @Column(name = "playground_name", nullable = false)
    private String playgroundName;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "playground_sports",
            joinColumns = @JoinColumn(name = "playground_id", referencedColumnName = "playground_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "sport_id"))
    private List<Sport> sports;

    @OneToOne
    @JoinColumn(name = "pg_availability_id")
    private PlaygroundAvailability playgroundAvailability;

    @OneToMany(mappedBy = "playground")
    private List<Booking> bookingList;

}
