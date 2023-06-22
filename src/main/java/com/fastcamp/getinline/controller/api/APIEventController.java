package com.fastcamp.getinline.controller.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class APIEventController {

    @GetMapping("/events")
    public String getEvents() {
        return "events";
    }

    @PostMapping("/events")
    public Boolean createEvent() {
        return true;
    }

    @GetMapping("/events/{eventId}")
    public String getEvent(@PathVariable long eventId) {
        return "eventDetail";
    }

    @PutMapping("/events/{eventId}")
    public Boolean updateEvent(@PathVariable long eventId) {
        return true;
    }

    @DeleteMapping("/events/{eventId}")
    public Boolean deleteEvent(@PathVariable long eventId) {
        return true;
    }
}
