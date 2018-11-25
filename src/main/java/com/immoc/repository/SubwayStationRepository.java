package com.immoc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.immoc.entity.SubwayStation;

/**
 * Created by 瓦力.
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}
