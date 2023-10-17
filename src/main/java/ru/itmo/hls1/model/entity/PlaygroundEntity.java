package ru.itmo.hls1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlaygroundEntity implements Serializable {
    private int playgroundId;
    private String location;
    private String playgroundName;
}
