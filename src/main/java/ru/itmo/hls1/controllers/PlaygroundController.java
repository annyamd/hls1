package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.PlaygroundDTO;
import ru.itmo.hls1.sevice.PlaygroundService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/playgrounds")
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllPlaygrounds(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
//        List<PlaygroundDTO> dtos = new ArrayList<>();
//        for (Playground p : playgroundService.findAllPlaygrounds()) {
//            dtos.add(new PlaygroundDTO(p.getId(), p.getPlaygroundName(), p.getLocation()));
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{playgroundId}")
    public ResponseEntity<?> getPlaygroundById(@PathVariable long playgroundId) {
//        Playground pg = playgroundService.getPlayground(id);
//        return new PlaygroundDTO(pg.getId(), pg.getLocation(), pg.getPlaygroundName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createPlayground(@RequestBody PlaygroundDTO playground) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{playgroundId}")
    public ResponseEntity<?> updatePlayground(@PathVariable long playgroundId, @RequestBody PlaygroundDTO playground) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<?> deletePlayground(@PathVariable long playgroundId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}/info")
//    public PlaygroundAvailabilityDTO getPlaygroundInfo(@PathVariable Long id){
//        PlaygroundAvailability pga = playgroundService.getPlaygroundDetails(id);
//        Playground pg = pga.getPlayground();
//        return new PlaygroundAvailabilityDTO(pga.getPlaygroundAvailability_id(), pga.getAvailability(),
//                pga.getAvailable_from(), pga.getAvailable_to(),
//                new PlaygroundDTO(pg.getId(), pg.getLocation(), pg.getPlaygroundName()));
//    }


}
