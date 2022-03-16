package dev.alfredang.weather.weatherApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.alfredang.weather.weatherApp.model.LocationInfo;

@Repository
public interface LocationInfoRepository extends CrudRepository<LocationInfo, String>{
}
