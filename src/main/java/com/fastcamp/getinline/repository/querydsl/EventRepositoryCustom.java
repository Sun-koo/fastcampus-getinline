package com.fastcamp.getinline.repository.querydsl;

import com.fastcamp.getinline.constant.EventStatus;
import com.fastcamp.getinline.dto.EventViewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EventRepositoryCustom {
    Page<EventViewResponse> findEventViewPageBySearchParams(
            String placeName,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Pageable pageable
    );
}