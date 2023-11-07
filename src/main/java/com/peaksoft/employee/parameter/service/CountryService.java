package com.peaksoft.employee.parameter.service;

import com.peaksoft.employee.parameter.model.Country;
import com.peaksoft.employee.parameter.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> listOfCountry(){
        return countryRepository.findAll();
    }

    public void saveCountry(Country country){
        countryRepository.save(country);
    }

    public Country findCountryById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    public void deleteCountry(Country country) {
        countryRepository.delete(country);
    }

    public List<Country> findByKeyword(String keyword){
        return countryRepository.findByKeyword(keyword);
    }

    public List<Country> sortAscAndDesc(String field, String direction) {
        Sort sort;

        if (direction != null && direction.equalsIgnoreCase("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, field);
        } else {
            sort = Sort.by(Sort.Direction.DESC, field);
        }
        return countryRepository.findAll(sort);
    }

    public Page<Country> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return countryRepository.findAll(pageable);
    }

    public Page<Country> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase("ASC") ?
                Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

        return countryRepository.findAll(pageable);
    }
}
