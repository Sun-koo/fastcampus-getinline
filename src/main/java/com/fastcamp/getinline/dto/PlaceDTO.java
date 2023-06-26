package com.fastcamp.getinline.dto;

import com.fastcamp.getinline.constant.PlaceType;

public record PlaceDTO(
    PlaceType placeType,
    String placeName,
    String address,
    String phoneNumber,
    int capacity,
    String memo
) {
    public static PlaceDTO of(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        int capacity,
        String memo
    ) {
        return new PlaceDTO(placeType, placeName, address, phoneNumber, capacity, memo);
    }
}