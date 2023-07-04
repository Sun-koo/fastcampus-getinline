package com.fastcamp.getinline.service;

import com.fastcamp.getinline.constant.ErrorCode;
import com.fastcamp.getinline.dto.PlaceDTO;
import com.fastcamp.getinline.exception.GeneralException;
import com.fastcamp.getinline.repository.PlaceRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Transactional
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional(readOnly = true)
    public List<PlaceDTO> getPlaces(Predicate predicate) {
        try {
            return StreamSupport.stream(placeRepository.findAll(predicate).spliterator(), false)
                    .map(PlaceDTO::of)
                    .toList();
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    @Transactional(readOnly = true)
    public Optional<PlaceDTO> getPlace(Long placeId) {
        try {
            return placeRepository.findById(placeId).map(PlaceDTO::of);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean createPlace(PlaceDTO placeDto) {
        try {
            if (placeDto == null) {
                return false;
            }

            placeRepository.save(placeDto.toEntity());
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean modifyPlace(Long placeId, PlaceDTO dto) {
        try {
            if (placeId == null || dto == null) {
                return false;
            }

            placeRepository.findById(placeId)
                    .ifPresent(place -> placeRepository.save(dto.updateEntity(place)));

            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public boolean removePlace(Long placeId) {
        try {
            if (placeId == null) {
                return false;
            }

            placeRepository.deleteById(placeId);
            return true;
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

}