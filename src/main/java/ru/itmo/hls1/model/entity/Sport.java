package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.itmo.hls1.constant.SportType;


@Data
@Entity
@Table(name=sport)
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_id", unique = true, nullable = false)
    private Integer sport_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sport_name", nullable = false)
    private SportType sportType;

    @ManyToOne( fetch = FetchType.LAZY)
    private Playground playgrounds;

}
