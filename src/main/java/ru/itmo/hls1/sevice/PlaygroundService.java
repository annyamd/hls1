package ru.itmo.hls1.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hls1.model.dto.PlaygroundDTO;
import ru.itmo.hls1.model.dto.PlaygroundDetailsDTO;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundService {

    public List<PlaygroundDTO> findAllPlaygrounds(){
        List<PlaygroundDTO> list = new ArrayList<>();
        list.add(new PlaygroundDTO(0, "Name 0", "Location 0"));
        list.add(new PlaygroundDTO(1, "Name 1", "Location 1"));
        list.add(new PlaygroundDTO(2, "Name 2", "Location 2"));
        return list;
    }

    public PlaygroundDetailsDTO getPlaygroundDetails(int id) {
        //        get from repository and return info in PlaygroundDetailsDTO object
        return new PlaygroundDetailsDTO(new PlaygroundDTO(1, "Moscow, Street", "Sport center"));
    }



}
