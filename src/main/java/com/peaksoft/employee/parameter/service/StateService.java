package com.peaksoft.employee.parameter.service;

import com.peaksoft.employee.parameter.model.Country;
import com.peaksoft.employee.parameter.model.State;
import com.peaksoft.employee.parameter.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {
	private final StateRepository stateRepository;

	//Get All States
	public List<State> listOfState() {
		return stateRepository.findAll();
	}

	public State findStateById(Integer id) {
		return stateRepository.findById(id).orElse(null);
	}
	
	//Delete State
	public void deleteState(State state) {
		stateRepository.delete(state);
	}
	
	//Update State
	public void saveState(State state) {
		stateRepository.save(state);
	}

	// SORT, PAGE AND SEARCH
	public List<State> findByKeyword(String keyword){
		return stateRepository.findByKeyword(keyword);
	}

	public List<State> sortAscAndDesc(String field, String direction) {
		Sort sort;

		if (direction != null && direction.equalsIgnoreCase("ASC")) {
			sort = Sort.by(Sort.Direction.ASC, field);
		} else {
			sort = Sort.by(Sort.Direction.DESC, field);
		}
		return stateRepository.findAll(sort);
	}

	public Page<State> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		return stateRepository.findAll(pageable);
	}

	public Page<State> findAllWithSort(String field, String direction, int pageNumber) {
		Sort sort = direction.equalsIgnoreCase("ASC") ?
				Sort.by(field).ascending() : Sort.by(field).descending();

		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

		return stateRepository.findAll(pageable);
	}
}
