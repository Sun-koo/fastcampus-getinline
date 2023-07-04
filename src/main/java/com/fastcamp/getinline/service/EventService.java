package com.fastcamp.getinline.service;

import com.fastcamp.getinline.constant.ErrorCode;
import com.fastcamp.getinline.constant.EventStatus;
import com.fastcamp.getinline.domain.Event;
import com.fastcamp.getinline.domain.Place;
import com.fastcamp.getinline.dto.EventDTO;
import com.fastcamp.getinline.dto.EventViewResponse;
import com.fastcamp.getinline.exception.GeneralException;
import com.fastcamp.getinline.repository.EventRepository;
import com.fastcamp.getinline.repository.PlaceRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Transactional
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    @Transactional(readOnly = true)
    public List<EventDTO> getEvents(Predicate predicate) {
        try {
            return StreamSupport.stream(eventRepository.findAll(predicate).spliterator(), false)
                    .map(EventDTO::of)
                    .toList();
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public Page<EventViewResponse> getEventViewResponse(
            String placeName,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Pageable pageable
    ) {
        try {
            return eventRepository.findEventViewPageBySearchParams(
                    placeName,
                    eventName,
                    eventStatus,
                    eventStartDatetime,
                    eventEndDatetime,
                    pageable
            );
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public Optional<EventDTO> getEvent(Long eventId) {
        try {
            return eventRepository.findById(eventId).map(EventDTO::of);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public Page<EventViewResponse> getEvent(Long placeId, Pageable pageable) {
        try {
            Place place = placeRepository.getReferenceById(placeId);
            Page<Event> eventPage = eventRepository.findByPlace(place, pageable);

            return new PageImpl<>(
                    eventPage.getContent()
                            .stream()
                            .map(event -> EventViewResponse.from(EventDTO.of(event)))
                            .toList(),
                    eventPage.getPageable(),
                    eventPage.getTotalElements()
            );
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean upsertEvent(EventDTO eventDto) {
        try {
            if (eventDto.id() != null) {
                return updateEvent(eventDto.id(), eventDto);
            } else {
                return createEvent(eventDto);
            }
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean createEvent(EventDTO eventDTO) {
        try {
            if (eventDTO == null) {
                return false;
            }

            Place place = placeRepository.getReferenceById(eventDTO.placeDto().id());
            eventRepository.save(eventDTO.toEntity(place));
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean updateEvent(Long eventId, EventDTO dto) {
        try {
            if (eventId == null || dto == null) {
                return false;
            }

            eventRepository.findById(eventId)
                    .ifPresent(event -> eventRepository.save(dto.updateEntity(event)));

            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean deleteEvent(Long eventId) {
        try {
            if (eventId == null) {
                return false;
            }

            eventRepository.deleteById(eventId);
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }
}
