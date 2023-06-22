package com.fastcamp.getinline.controller.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public String getPlaces() {
        return "places";
    }

    @PostMapping("/places")
    public Boolean createPlace() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public String getPlace(@PathVariable long placeId) {
        return "placeDetail";
    }

    @PutMapping("/places/{placeId}")
    public Boolean updatePlace(@PathVariable long placeId) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean deletePlace(@PathVariable long placeId) {
        return true;
    }
}
