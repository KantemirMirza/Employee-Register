package com.peaksoft.employee.parameter.service;

import com.peaksoft.employee.parameter.model.Country;
import com.peaksoft.employee.parameter.model.Location;
import com.peaksoft.employee.parameter.model.State;
import com.peaksoft.employee.parameter.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LocationService {
	private final LocationRepository locationRepository;

	public List<Location> listOfLocation() {
		return (List<Location>) locationRepository.findAll();
	}

	public void saveLocation(Location location) {
		locationRepository.save(location);
	}

	public Location findLocationById(Integer id) {
		return locationRepository.findById(id).orElse(null);
	}

	public void deleteLocation(Location location) {
		locationRepository.delete(location);
	}

	// SORT, PAGE AND SEARCH
	public List<Location> findByKeyword(String keyword){
		return locationRepository.findByKeyword(keyword);
	}
}
