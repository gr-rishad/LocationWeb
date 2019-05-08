package com.rishad.location.web.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rishad.location.web.entities.Location;
import com.rishad.location.web.repos.LocationRepository;
import com.rishad.location.web.services.LocationService;
import com.rishad.location.web.util.EmailUtil;
import com.rishad.location.web.util.ReportUtil;

@Controller
public class LocationController {
	
	@Autowired
	private LocationService locationsService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;

	
	@RequestMapping("/showCreateLocation")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLocation")
	public String saveLocations(@ModelAttribute("location") Location location,ModelMap modelMap) {
		Location locationSaved= locationsService.saveLocation(location);
		String msg= "Location Savedwith id "+locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("PUT YOUR EMAIL", "Location Saved", 
				"Location Saved Successfully and about to return a response");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocation(ModelMap modelMap) {
		List<Location> locations = locationsService.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id,ModelMap modelMap) {
		
		// Location location = new Location();
		// location.setId(id);
		// locationService.deleteLocation(location);
		
		Location deleteLocation= locationsService.getLocationById(id);
		locationsService.deleteLocation(deleteLocation);
		List<Location> locations= locationsService.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/updateLocation")
	public String updateLocations(@RequestParam("id")int id,ModelMap modelMap) {
		Location location = locationsService.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location,ModelMap modelMap) {
		
		locationsService.updateLocation(location);
		List<Location> locations = locationsService.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		
		String path = sc.getRealPath("/");
		List<Object[]> data = locationRepository.findTypeAndTypeCount();
        reportUtil.generateReportChart(path, data);
		return "report";
		
	}
}






