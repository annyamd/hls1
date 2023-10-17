package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.model.entity.PlaygroundEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundService {

    public List<PlaygroundEntity> findAllPlaygrounds(){
        List<PlaygroundEntity> list = new ArrayList<>();
        list.add(new PlaygroundEntity(0, "Name 0", "Location 0"));
        list.add(new PlaygroundEntity(1, "Name 1", "Location 1"));
        list.add(new PlaygroundEntity(2, "Name 2", "Location 2"));
        return list;
    }

}
