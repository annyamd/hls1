package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hls1.model.entity.PlaygroundEntity;
import ru.itmo.hls1.sevice.PlaygroundService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class PlaygroundListController {

    private PlaygroundService playgroundService;

    @Autowired
    public PlaygroundListController(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PlaygroundEntity>> getAllPlaygrounds(){
        return ResponseEntity.ok(playgroundService.findAllPlaygrounds());
    }

}
