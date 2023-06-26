package com.fastcamp.getinline.domain;

import com.fastcamp.getinline.constant.PlaceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Place {
    private long id;

    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private int capacity;
    private String memo;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Place of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return new Place(placeType, placeName, address, phoneNumber, capacity, memo);
    }
}
