package com.rishad.location.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishad.location.web.entities.Location;
import com.rishad.location.web.repos.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public void deleteLocation(Location location) {

		locationRepository.delete(location);
	}

	@Override
	public Location getLocationById(int id) {
		return locationRepository.findById(id).get();
	}

	@Override
	public List<Location> getAllLocation() {
		return locationRepository.findAll();
	}

}
