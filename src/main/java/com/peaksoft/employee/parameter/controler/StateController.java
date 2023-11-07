package com.peaksoft.employee.parameter.controler;

import com.peaksoft.employee.parameter.model.Country;
import com.peaksoft.employee.parameter.model.State;
import com.peaksoft.employee.parameter.service.CountryService;
import com.peaksoft.employee.parameter.service.StateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StateController {
	private final UserDetailsService userDetailsService;
	private final StateService stateService;
	private final CountryService countryService;

	@GetMapping("/state")
	public String state(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		return"parameter/state/index";
	}

	@GetMapping("/states")
	public String listOfState(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("listOfSate", stateService.listOfState());
		return"parameter/state/listOfState";
	}

	@GetMapping("/addState")
	public String addState(Model model, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("createState", new State());
		model.addAttribute("listOfCountry", countryService.listOfCountry());
		return"parameter/state/addState";
	}

	@PostMapping("/addState")
	public String saveState(@ModelAttribute("createState") @Valid State state, BindingResult result){
		if (result.hasErrors()) {
			return"parameter/state/addState";
		}
		stateService.saveState(state);
		return"redirect:/states";
	}

	@GetMapping("/states/{id}/edit")
	public String editState(@PathVariable Integer id, Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("editState", stateService.findStateById(id));
		model.addAttribute("listOfCountry", countryService.listOfCountry());
		return "parameter/state/editState";
	}

	@PostMapping("/states/{id}/edit")
	public String updateState(@PathVariable Integer id, @ModelAttribute("editState")
	                           @Valid State updatedState, BindingResult result) {

		if (result.hasErrors()) {
			return "parameter/state/editState";
		}
		State state = stateService.findStateById(id);
		state.setCountry(updatedState.getCountry());
		state.setStateName(updatedState.getStateName());
		state.setCapital(updatedState.getCapital());
		state.setCode(updatedState.getCode());
		state.setDetails(updatedState.getDetails());
		stateService.saveState(state);
		return "redirect:/states";
	}

	@GetMapping("/states/{id}/delete")
	public String deleteState(@PathVariable Integer id){
		State state = stateService.findStateById(id);
		stateService.deleteState(state);
		return"redirect:/states";
	}

	@GetMapping("/states/{id}/info")
	public String getInfoState(Model model, @PathVariable Integer id, Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("infoState", stateService.findStateById(id));
		return"parameter/state/infoState";
	}

	// SORT, PAGE AND SEARCH

	@PostMapping("/states/search")
	public String search(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		model.addAttribute("listOfState", stateService.findByKeyword(keyword));
		return "parameter/state/listOfState";
	}

	@GetMapping("/states/pages/{pageNumber}")
	public String findPage(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<State> page = stateService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalElements = page.getTotalElements();
		List<State> states = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("listOfState", states);

		return "parameter/state/listOfState";
	}

	@GetMapping("/states/pages/{pageNumber}/{field}/{sortDir}")
	public String findAllWithSort(Model model, @PathVariable("pageNumber") int currentPage,
								  @PathVariable("field") String field,
								  @PathVariable("sortDir") String sortDir) {
		Page<State> page = stateService.findAllWithSort(field, sortDir, currentPage);
		List<State> states = page.getContent();
		int totalPages = page.getTotalPages();
		long totalElements = page.getTotalElements();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("ASC") ? "DESC" : "ASC");
		model.addAttribute("listOfState", states);

		return "parameter/state/listOfState";
	}

	@GetMapping("/states/sorts/{field}")
	public String sortAscAndDesc(Model model, @PathVariable("field") String field,
								 @RequestParam("sortDir") String sortDir) {
		List<State> states = stateService.sortAscAndDesc(field, sortDir);

		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("ASC") ? "DESC" : "ASC");
		model.addAttribute("listOfState", states);

		return "parameter/state/listOfState";
	}
}
