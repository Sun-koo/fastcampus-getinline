package com.fastcamp.getinline.repository;

import com.fastcamp.getinline.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
