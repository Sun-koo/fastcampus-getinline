package com.fastcamp.getinline.controller.api;

import com.fastcamp.getinline.constant.PlaceType;
import com.fastcamp.getinline.dto.APIDataResponse;
import com.fastcamp.getinline.dto.PlaceDTO;
import com.fastcamp.getinline.dto.PlaceRequest;
import com.fastcamp.getinline.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api")
//@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getPlaces() {
        return APIDataResponse.of(List.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/places")
    public APIDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
        return APIDataResponse.empty();
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceResponse> getPlace(@PathVariable long placeId) {
        if (placeId == 2L) {
            return APIDataResponse.empty();
        }

        return APIDataResponse.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public APIDataResponse<Void> updatePlace(
            @PathVariable long placeId,
            @RequestBody PlaceRequest placeRequest
    ) {
        return APIDataResponse.empty();
    }

    @DeleteMapping("/places/{placeId}")
    public APIDataResponse<Void> deletePlace(@PathVariable long placeId) {
        return APIDataResponse.empty();
    }
}
