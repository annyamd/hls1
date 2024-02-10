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

    @Column(name = "latitude", nullable = false)
    private Float latitude;

    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @ManyToMany
    @JoinTable(name = "playground_sports",
            joinColumns = @JoinColumn(name = "playground_id", referencedColumnName = "playground_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "sport_id"))
    private List<Sport> sports;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pg_availability_id")
    private PlaygroundAvailability playgroundAvailability;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL)
    private List<Booking> bookingList;

}
