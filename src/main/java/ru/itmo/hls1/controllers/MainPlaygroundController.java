package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.PlaygroundAvailabilityDTO;
import ru.itmo.hls1.model.dto.PlaygroundDTO;
import ru.itmo.hls1.model.dto.PlaygroundDetailsDTO;
import ru.itmo.hls1.model.entity.Playground;
import ru.itmo.hls1.model.entity.PlaygroundAvailability;
import ru.itmo.hls1.sevice.PlaygroundService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/playgrounds")
public class MainPlaygroundController {

    private final PlaygroundService playgroundService;

    @GetMapping(value = "/")
    public List<PlaygroundDTO> getAllPlaygrounds(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String location){
        List<PlaygroundDTO> dtos = new ArrayList<>();
        for (Playground p : playgroundService.findAllPlaygrounds()) {
            dtos.add(new PlaygroundDTO(p.getId(), p.getPlaygroundName(), p.getLocation()));
        }
        return dtos;
    }

    @GetMapping(value = "/{id}")
    public PlaygroundDTO getPlayground(@PathVariable Long id){
        Playground pg = playgroundService.getPlayground(id);
        return new PlaygroundDTO(pg.getId(), pg.getLocation(), pg.getPlaygroundName());
    }

    @GetMapping(value = "/{id}/info")
    public PlaygroundAvailabilityDTO getPlaygroundInfo(@PathVariable Long id){
        PlaygroundAvailability pga = playgroundService.getPlaygroundDetails(id);
        Playground pg = pga.getPlayground();
        return new PlaygroundAvailabilityDTO(pga.getPlaygroundAvailability_id(), pga.getAvailability(),
                pga.getAvailable_from(), pga.getAvailable_to(),
                new PlaygroundDTO(pg.getId(), pg.getLocation(), pg.getPlaygroundName()));
    }


}
