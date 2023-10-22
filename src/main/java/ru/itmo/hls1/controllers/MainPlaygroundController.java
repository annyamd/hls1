package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.PlaygroundDTO;
import ru.itmo.hls1.model.dto.PlaygroundDetailsDTO;
import ru.itmo.hls1.sevice.PlaygroundService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPlaygroundController {

    private PlaygroundService playgroundService;

    @Autowired
    public MainPlaygroundController(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @GetMapping(value = "/playgrounds")
    public List<PlaygroundDTO> getAllPlaygrounds(){
        return playgroundService.findAllPlaygrounds();
    }

    @GetMapping(value = "/playgrounds/{id}")
    public PlaygroundDetailsDTO getPlaygroundDetails(@PathVariable int id){
//        pg, availability, booking
        return playgroundService.getPlaygroundDetails(id);
    }

}
