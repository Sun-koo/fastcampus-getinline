package com.fastcamp.getinline.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminPlaceMap {
    private long id;

    private long adminId;
    private long placeId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
