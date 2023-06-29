package com.fastcamp.getinline.controller.api;

import com.fastcamp.getinline.constant.EventStatus;
import com.fastcamp.getinline.dto.APIDataResponse;
import com.fastcamp.getinline.dto.EventRequest;
import com.fastcamp.getinline.dto.EventResponse;
import com.fastcamp.getinline.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//@Validated
@RequiredArgsConstructor
//@RequestMapping("/api")
//@RestController
public class APIEventController {

    private final EventService eventService;

    @GetMapping("/events")
    public APIDataResponse<List<EventResponse>> getEvents(
            @Positive Long placeId,
            @Size(min = 2) String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDateTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDateTime
    ) {
        List<EventResponse> response = eventService
                .getEvents(placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime)
                .stream().map(EventResponse::from).toList();

        return APIDataResponse.of(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events")
    public APIDataResponse<String> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        boolean result = eventService.createEvent(eventRequest.toDTO());

        return APIDataResponse.of(Boolean.toString(result));
    }

    @GetMapping("/events/{eventId}")
    public APIDataResponse<EventResponse> getEvent(@Positive @PathVariable Long eventId) {
        EventResponse eventResponse = EventResponse.from(eventService.getEvent(eventId).orElse(null));

        return APIDataResponse.of(eventResponse);
    }

    @PutMapping("/events/{eventId}")
    public APIDataResponse<String> updateEvent(
            @Positive @PathVariable Long eventId,
            @Valid @RequestBody EventRequest eventRequest
    ) {
        boolean result = eventService.updateEvent(eventId, eventRequest.toDTO());
        return APIDataResponse.of(Boolean.toString(result));
    }

    @DeleteMapping("/events/{eventId}")
    public APIDataResponse<String> deleteEvent(@Positive @PathVariable Long eventId) {
        boolean result = eventService.deleteEvent(eventId);

        return APIDataResponse.of(Boolean.toString(result));
    }

}
