package com.fastcamp.getinline.dto;

import com.fastcamp.getinline.constant.EventStatus;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        PlaceDTO place,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDatetime,
        LocalDateTime eventEndDatetime,
        Integer currentNumberOfPeople,
        Integer capacity,
        String memo
) {

    public static EventResponse of(
            Long id,
            PlaceDTO place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventResponse(
                id,
                place,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                memo
        );
    }

    public static EventResponse from(EventDTO eventDTO) {
        if (eventDTO == null) { return null; }
        return EventResponse.of(
                eventDTO.id(),
                eventDTO.placeDto(),
                eventDTO.eventName(),
                eventDTO.eventStatus(),
                eventDTO.eventStartDatetime(),
                eventDTO.eventEndDatetime(),
                eventDTO.currentNumberOfPeople(),
                eventDTO.capacity(),
                eventDTO.memo()
        );
    }

    public static EventResponse empty(PlaceDTO placeDto) {
        return EventResponse.of(null, placeDto, null, null, null, null, null, null, null);
    }

    public String getPlaceName() {
        return this.place().placeName();
    }
}
