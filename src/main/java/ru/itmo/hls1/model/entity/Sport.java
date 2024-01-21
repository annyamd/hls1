package ru.itmo.hls1.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.itmo.hls1.model.constant.SportType;

import java.util.List;


@Data
@Entity
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_id")
    private long sportId;

    @Column(name = "sport_name", nullable = false)
    private String sportName;

}
