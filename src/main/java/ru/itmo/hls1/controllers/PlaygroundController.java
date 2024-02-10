package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.PlaygroundDTO;
import ru.itmo.hls1.sevice.PlaygroundService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/playgrounds")
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllPlaygrounds(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        List<PlaygroundDTO> playgroundDTOs = playgroundService.findAllPlaygrounds(page, size);
        return ResponseEntity.ok().body(playgroundDTOs);
    }

    @GetMapping(value = "/{playgroundId}")
    public ResponseEntity<?> getPlaygroundById(@PathVariable long playgroundId) {
        PlaygroundDTO pg = playgroundService.getPlayground(playgroundId);
        return ResponseEntity.ok().body(pg);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createPlayground(@RequestBody PlaygroundDTO playground) {
//        check if already exists (by validator)
        PlaygroundDTO created = playgroundService.createPlayground(playground);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{playgroundId}")
    public ResponseEntity<?> updatePlayground(@PathVariable long playgroundId, @RequestBody PlaygroundDTO playground) {
        PlaygroundDTO updated = playgroundService.updatePlayground(playgroundId, playground);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<?> deletePlayground(@PathVariable long playgroundId) {
        playgroundService.deletePlayground(playgroundId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
