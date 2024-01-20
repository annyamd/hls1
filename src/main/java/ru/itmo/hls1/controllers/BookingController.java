package ru.itmo.hls1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls1.model.dto.BookingDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/booking")
public class BookingController {

    @PostMapping(value = "/")
    public ResponseEntity<?> createBookingRecord(@RequestBody BookingDTO newRecord) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllRecords(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{recordId}")
    public ResponseEntity<?> getRecordById(@PathVariable long recordId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{recordId}")
    public ResponseEntity<?> deleteBookingRecord(@PathVariable long recordId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{recordId}")
    public ResponseEntity<?> updateBookingRecord(@PathVariable long recordId, @RequestBody BookingDTO record) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
