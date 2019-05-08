package com.rishad.location.web.services;

import java.util.List;

import com.rishad.location.web.entities.Location;

public interface LocationService {
	
	
	Location saveLocation(Location location);
	Location updateLocation(Location location);
	void deleteLocation(Location location);
	Location getLocationById(int id);
	List<Location> getAllLocation();

}
