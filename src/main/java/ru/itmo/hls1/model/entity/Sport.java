package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.itmo.hls1.model.constant.SportType;


@Data
@Entity
@Table(name="sporttype")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer sport_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sport_name", nullable = false)
    private SportType sportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "playground_id", nullable = false)
    private Playground playground;

}
