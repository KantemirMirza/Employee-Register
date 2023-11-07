package com.peaksoft.employee.parameter.controler;

import com.peaksoft.employee.parameter.model.Location;
import com.peaksoft.employee.parameter.service.CountryService;
import com.peaksoft.employee.parameter.service.LocationService;
import com.peaksoft.employee.parameter.service.StateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LocationController {
	private final UserDetailsService userDetailsService;
	private final LocationService locationService;
	private final CountryService countryService;
	private final StateService stateService;

	@GetMapping("/location")
	public String location(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		return"parameter/location/index";
	}

	@GetMapping("/locations")
	public String listOfLocation(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("listOfLocation", locationService.listOfLocation());
		return"parameter/location/listOfLocation";
	}

	@GetMapping("/addLocation")
	public String addLocation(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("createLocation", new Location());
		model.addAttribute("listOfState", stateService.listOfState());
		return"parameter/location/addLocation";
	}

	@PostMapping("/addLocation")
	public String saveLocation(@ModelAttribute("createLocation") @Valid Location location, BindingResult result){
		if (result.hasErrors()) {
			return"parameter/location/addLocation";
		}
		locationService.saveLocation(location);
		return"redirect:/locations";
	}

	@GetMapping("/locations/{id}/edit")
	public String editLocation(@PathVariable Integer id, Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("editLocation", locationService.findLocationById(id));
		model.addAttribute("listOfState", stateService.listOfState());
		return "parameter/location/editLocation";
	}

	@PostMapping("/locations/{id}/edit")
	public String updateLocation(@PathVariable Integer id, @ModelAttribute("editLocation")@Valid Location loc, BindingResult result) {

		if (result.hasErrors()) {
			return "parameter/location/editLocation";
		}

		Location location = locationService.findLocationById(id);
		location.setState(loc.getState());
		location.setCity(loc.getCity());
		location.setAddress(loc.getAddress());
		location.setDetails(loc.getDetails());
		locationService.saveLocation(location);
		return "redirect:/locations";
	}

	@GetMapping("/locations/{id}/delete")
	public String deleteLocation(@PathVariable Integer id){
		Location location = locationService.findLocationById(id);
		locationService.deleteLocation(location);
		return"redirect:/locations";
	}

	@GetMapping("/locations/{id}/info")
	public String infoLocation(Model model, @PathVariable Integer id, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("infoLocation", locationService.findLocationById(id));
		return"parameter/location/infoLocation";
	}
}
